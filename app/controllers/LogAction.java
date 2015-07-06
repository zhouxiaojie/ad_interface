package controllers;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.sql.DataSource;

import play.db.DB;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;
import com.ocean.util.DateUtil;

import controllers.request.AdEventRequest;
import controllers.request.AdEventResponse;
import controllers.request.TerminalInfo;

public class LogAction extends Controller{
	
	@BodyParser.Of(BodyParser.Json.class)
	public static Result log() {
		JsonNode content= request().body().asJson();
		AdEventRequest req=Json.fromJson(content, AdEventRequest.class);
		insertLogEvent(req);
		AdEventResponse resp = new AdEventResponse(200);
        return ok(Json.toJson(resp));
    }
	
	private static void insertLogEvent(AdEventRequest req){
		DataSource ds = DB.getDataSource();
		java.sql.Connection conn=null;
		PreparedStatement stmt=null;
		try {
			conn = ds.getConnection();
			Date now =new Date();
			String sql = "insert into log_event_"+DateUtil.DateYYMMDDFmt(now)+" ("
					+ "package_name,model,manu,os,imsi,imei,mac,appid,event,args,min_date,create_time)"
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			TerminalInfo ti = req.getTerminal();
			stmt.setString(1,ti.getPackagename());
			stmt.setString(2,ti.getModel());
			stmt.setString(3,ti.getManu());
			stmt.setString(4,ti.getOs());
			stmt.setString(5,ti.getImsi());
			stmt.setString(6,ti.getImei());
			stmt.setString(7,ti.getMac());
			stmt.setString(8,ti.getAppid());
			stmt.setString(9,req.getEvent());
			stmt.setString(10,req.getArgs2Str());
			stmt.setString(11,DateUtil.DateMinFmt(now));
			stmt.setTimestamp(12,new Timestamp(now.getTime()));
			
			stmt.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				if(stmt!=null)stmt.close();
				if(conn!=null)conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
	}
}
