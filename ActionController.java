package com.qualitia.licensing.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//import com.qualitia.licensing.service.LicensingService;
import com.webdriverfw.Wrappers.ActionReturnInfo;
import com.webdriverfw.Wrappers.General;

import java.io.IOException;
import java.lang.Class;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@RestController
@SpringBootApplication
@EnableWebMvc
@RequestMapping("/actions/api/")
public class ActionController {
    private static final String VENDOR_CODE = new String("J0ZfGN1/HY3KvMh10jv/6k04ij+MVk/0b26HGlAT4cnnwNvFaxRV0Ri+t2crbvOZjWvUcu9XXU3/1qvmFnXbpzsV7km/2ZuJPLa46jRNGoGAFsHg7IQhZTr6iBauGBr/7WWIartQtb25sTqMVzjqtQGHYICeU+Zv/zECgP6gAnMClyhL4bRPNIm4uLCbGacZHBm2hPFrXDnr4eeu4SETvs0Kk6S8+3gGm1PuOBGhbrOl5AuqtRC9nzwu3ixtUfFzYWbOXx1ur0xn+EI3nHg2QsR+IBmhPNZ2aVYU2D4L4WXu5CHIxDzj+1Zr/+t1rTP6xgWcYK/+gvFThXskhG+3u0OtZar49cJbZUyUbAndbaJjpBiP0LZgM1lUGekZJfqtTKYv7lQSlMubFIOvIWIJ5q+Rb5H4NpXJWjuWbJfsIXvTgy5HmW53wk3ujwVJKRsc4cgPkKu4gcMz/lvMVRktO/ia4DungOk6pj9cUCsi769qAmDiCCE57H++lEYe8P3Q+vcuDZ4RB62SnqL+4ZQ0NDe+Y2HVZfeU4tQdsfT4EmaAhZUjZx8OB4pm24ERrKRQopOetqhDmeyJbKXC+lSsJskvD1+UCuYG85iTd329Qq95Vm1Zjamrf9Hes7Sd97D06U563addWrhiD/npxS+e37BIHpdb0myoSsdI+0BodNdkBkXzWy/jQ6vPVAHmtvmseBY1Uf5O56/POfkrMqWW758QGrUeOno5aT25Dvw36WF2oSYokMQe2okcAghrSBcMB8+CwKFNBDDx/Erg9Tkw6/OsZ/K3N8mCNkHBRuXM3G8lC4vWmfdxI6Q9Y6nREkGT//txIWrDpAJ7eRmihUzIPCTtgZl+M8wizlcw68HO7a8xekl7koAEUrooZIPfseg8+VL4ZHW8SqtROmgNxjIxuwy8K4b6BKZxsvGzXk8Wb46CDj8un7crt7Z1PLfImeQ3y7pn4ml5XXRzRJIYNTU+6w==");
    private static final String scope = new String(
            "<haspscope>\n" + " <license_manager hostname=\"localhost\" />\n" + "</haspscope>\n");
    ActionReturnInfo returnInfo = new ActionReturnInfo();
    General gen =new General(returnInfo);
    static int setData = 0;
    static Instant before = Instant.now();
    static Instant after = Instant.now();
    ArrayList<String> qtpCommandList = new ArrayList<String>();
    private static final String scope1 = new String("<haspscope />\n");
    
    private static final Logger logger = LoggerFactory.getLogger(ActionController.class);

    public static final String view = new String(
    		"<haspformat root=\"my_custom_scope\">\n" +
    				"  <hasp>\n" +
    				"    <attribute name=\"id\" />\n" +
    				"    <attribute name=\"type\" />\n" +
    				"    <feature>\n" +
    				"      <attribute name=\"id\" />\n" +
    				"      <element name=\"concurrency\" />\n" +
    				"      <element name=\"license\" />\n" +
    				"      <session>\n" +
    				"        <element name=\"username\" />\n" +
    				"        <element name=\"hostname\" />\n" +
    				"        <element name=\"ip\" />\n" +
    				"        <element name=\"apiversion\" />\n" +
    				"      </session>\n" +
    				"    </feature>\n" +
    				"  </hasp>\n" +
    		"</haspformat>\n");

	@Autowired
	// LicensingService licensingService;

	/* API for getting licensing information */
	@RequestMapping(value = "/1.0/ping", method = RequestMethod.GET)
	public String getLicenseInfo() {
		//logger.debug(System.getProperty("java.library.path"));
		//return licensingService.getLicensingInfoByVendorCode(scope, view, VENDOR_CODE);
		System.out.println("ddd");
		return "running";
	}
	

	
	/* API for getting licensing information */
	@RequestMapping(value = "/1.0/run", method = RequestMethod.POST)
	public ActionReturnInfo actionRouter(
			@RequestHeader("actionName") String actionName, 
			@RequestHeader("argList") String argList, 
			@RequestHeader("argType") String argType, 
			@RequestHeader("delimitor") String delimitor) throws Exception {

		System.out.println(actionName);
		System.out.println(argList);
		ExecuteAPIAction(actionName, argList, delimitor);
		//invokeKeywordThruReflection(actionName,argType,argList, delimitor);
		return returnInfo;
		
	}
	
	public int ExecuteAPIAction(String actionName, String ArgDetails, String delimitor) throws Exception {
		int retData = -1;
		String UserName = null;
		String ApiMethod ="" ;
		String AuthenticationType ="" ;
		String BasePath ="" ;
		String BaseURL ="" ;
		String ExpectedHTTPStatusCode ="" ;
		String HeaderLabel ="" ;
		String Password ="" ;
		String RequestHeaders ="" ;
		String RequestJsonFilePath ="" ;
		String RequestMethod ="" ;
		String ResponseFilePath ="" ;
		String ResultJsonFilePath ="" ;
		String SSLFlag ="" ;
		Object FormData = null;
		String BaseUrl = null;
		
		String action = actionName;
		String[] a = ArgDetails.split(delimitor);
		
		for (int i=0; i<a.length;i++) {
			if (a[i].equals("vbempty")) {
				a[i] = "";
			}
		}
		
		switch(action){ 
		case "SetRestBasicAuth" : {  
			
			gen.SetRestBasicAuth (a[0], a[1]);
			break;
		}
		case "SetRestBearerAuth" : {  
				//retData = gen.SetRestBearerAuth (Token);
				retData = gen.SetRestBearerAuth (a[0]);
				break;
		}
		case "SetRestDigestAuth" : {  
				retData = gen.SetRestDigestAuth (a[0], a[1]);
				break;
		}
		case "SetRestJWTAuth" : {  
				retData = gen.SetRestJWTAuth (a[0]);
				break;
		}
		case "ExecuteDeleteAPIService" : {  
				//retData = gen.ExecuteDeleteAPIService (BaseURL, BasePath, RequestFilePath, AuthenticationType, HeaderLabel, SSLFlag, ResponseFilePath, ExpectedHTTPStatusCode);
			retData = gen.ExecuteDeleteAPIService (a[0], a[1], a[2], a[3], a[4], a[5], a[6], a[7]);
				break;
		}
		case "ExecuteDeleteAPIServiceAndDownloadWithRawData" : {  
				//retData = gen.ExecuteDeleteAPIServiceAndDownloadWithRawData (BaseURL,BasePath,RequestFilepath,AuthenticationType,HeaderLabel,SSLFlag,ResponseFilePath,DownloadLocation,ExpectedHTTPStatusCode);
				retData = gen.ExecuteDeleteAPIServiceAndDownloadWithRawData (a[0],a[1],a[2],a[3],a[4],a[5],a[6],a[7],a[8]);
				break;
		}
		case "ExecuteDeleteAPIServiceWithFormData" : {  
				retData = gen.ExecuteDeleteAPIServiceWithFormData (BaseURL, BasePath, FormData, AuthenticationType, HeaderLabel, SSLFlag, ResponseFilePath, ExpectedHTTPStatusCode);
				break;
		}
		case "ExecuteAPIService" : {  
				//retData = gen.ExecuteAPIService (URL, RequestMethod, RequestJsonFilePath, ResultJsonFilePath, AuthenticationType, UserName, Password, RequestHeaders, SSLCertificateVerification);
				retData = gen.ExecuteAPIService (a[0],a[1],a[2],a[3],a[4],a[5],a[6],a[7],a[8]);
				break;
		}
		case "ExecuteRestService" : {  
				
				retData = gen.ExecuteRestService (BaseUrl, ApiMethod, RequestMethod, RequestJsonFilePath, ResultJsonFilePath, AuthenticationType, UserName, Password, RequestHeaders);
				break;
		}
		case "ExecuteGetAPIService" : {  
				//retData = gen.ExecuteGetAPIService (BaseURL, BasePath, AuthenticationType, HeaderLabel, SSLFlag, ResponseFilePath, ExpectedHTTPStatusCode);
				retData = gen.ExecuteGetAPIService (a[0],a[1],a[2],a[3],a[4],a[5],a[6]);
				break;
				
		}
		case "ExecuteGetAPIServiceWithFormData" : {  
				//retData = gen.ExecuteGetAPIServiceWithFormData (BaseURL, BasePath, AuthenticationType, HeaderLabel, SSLFlag, ResponseFilePath, ExpectedHTTPStatusCode);
			retData = gen.ExecuteGetAPIServiceWithFormData (a[0],a[1],a[2],a[3],a[4],a[5],a[6]);
				break;
		}
		case "ExecutePostAPIService" : {  
				//retData = gen.ExecutePostAPIService (BaseURL, BasePath, RequestFilePath, AuthenticationType, HeaderLabel, SSLFlag, ResponseFilePath, ExpectedHTTPStatusCode);
			retData = gen.ExecutePostAPIService (a[0],a[1],a[2],a[3],a[4],a[5],a[6],a[7]);
				break;
		}
		case "ExecutePostAPIServiceAndDownloadWithRawData" : {  
				//retData = gen.ExecutePostAPIServiceAndDownloadWithRawData (BaseURL,BasePath,RequestFilepath,AuthenticationType,HeaderLabel,SSLFlag,ResponseFilePath,DownloadLocation,ExpectedHTTPStatusCode);
			retData = gen.ExecutePostAPIServiceAndDownloadWithRawData (a[0],a[1],a[2],a[3],a[4],a[5],a[6],a[7],a[8]);
			
				break;
		}
		case "ExecutePostAPIServiceWithFormData" : {  
				//retData = gen.ExecutePostAPIServiceWithFormData (BaseURL, BasePath, FormData, AuthenticationType, HeaderLabel, SSLFlag, ResponseFilePath, ExpectedHTTPStatusCode);
			retData = gen.ExecutePostAPIServiceWithFormData (a[0],a[1],a[2],a[3],a[4],a[5],a[6],a[7]);
				break;
		}
		case "ExecutePutAPIService" : {  
				//retData = gen.ExecutePutAPIService (BaseURL, BasePath, RequestFilePath, AuthenticationType, HeaderLabel, SSLFlag, ResponseFilePath, ExpectedHTTPStatusCode);
			retData = gen.ExecutePutAPIService (a[0],a[1],a[2],a[3],a[4],a[5],a[6],a[7]);
				break;
		}
		case "ExecutePutAPIServiceAndDownloadWithRawData" : {  
				//retData = gen.ExecutePutAPIServiceAndDownloadWithRawData (BaseURL,BasePath,RequestFilepath,AuthenticationType,HeaderLabel,SSLFlag,ResponseFilePath,DownloadLocation,ExpectedHTTPStatusCode);
			retData = gen.ExecutePutAPIServiceAndDownloadWithRawData (a[0],a[1],a[2],a[3],a[4],a[5],a[6],a[7],a[8]);
				break;
		}
		case "ExecutePutAPIServiceWithFormData" : {  
				//retData = gen.ExecutePutAPIServiceWithFormData (BaseURL, BasePath, FormData, AuthenticationType, HeaderLabel, SSLFlag, ResponseFilePath, ExpectedHTTPStatusCode);
			retData = gen.ExecutePutAPIServiceWithFormData (a[0],a[1],a[2],a[3],a[4],a[5],a[6],a[7]);
				break;
		}
		case "ImportSSLCertificate" : {  
				//retData = gen.ImportSSLCertificate (CertificatePath, Alias, Password);
			retData = gen.ImportSSLCertificate (a[0],a[1],a[2]);
				break;
		}
		case "RemoveGenericHeaders" : {  
				retData = gen.RemoveGenericHeaders (a[0]);
				break;
		}
		case "SetRestFormUrlEncodedParameters" : {  
				retData = gen.SetRestFormUrlEncodedParameters (a[0],a[1]);
				break;
		}
		case "SetRestHeaders" : {  
				retData = gen.SetRestHeaders (a[0],a[1],a[2]);
				break;
		}
		case "SetRestQueryParameters" : {  
				retData = gen.SetRestQueryParameters (a[0],a[1]);
				break;
		}
		case "SetRestTimeOut" : {  
				retData = gen.SetRestTimeOut (a[0]);
				break;
		}
		case "WaitForRestResponseAttribute" : {  
				retData = gen.WaitForRestResponseAttribute (a[0],a[1],a[2],a[3]);
				break;
		}
		case "VerifyRestResponseTime" : {  
				retData = gen.VerifyRestResponseTime (a[0],a[1],a[2],a[3]);
				break;
		}
		case "StoreRestResponseHeaders" : {  
				retData = gen.StoreRestResponseHeaders (a[0]);
				break;
		}
		case "StoreResponseHeaderValue" : {  
				retData = gen.StoreResponseHeaderValue (a[0],a[1]);
				break;
		}
		case "StoreRestResponseTime" : {  
				retData = gen.StoreRestResponseTime (a[0]);
				break;
		}
		case "StoreRestResponseStatus" : {  
				retData = gen.StoreRestResponseStatus (a[0]);
				break;
		}
		case "VerifyRestResponseheaders" : {  
				retData = gen.VerifyRestResponseheaders (a[0],a[1],a[2]);
				break;
		}
		case "VerifyRestResponseStatus" : {  
				retData = gen.VerifyRestResponseStatus (a[0],a[1]);
				break;
		}
		case "AddNodeInJson" : {  
				retData = gen.AddNodeInJson (a[0],a[1],a[2],a[3],a[4]); 
				break;
		}
		case "CompareJson" : {  
				retData = gen.CompareJson (a[0],a[1],a[2],a[3]); 
				break;
		}
		case "ConvertStringToJsonFile" : {  
				retData = gen.ConvertStringToJsonFile (a[0],a[1]); 
				break;
		}
		case "RemoveNodeInJson" : {  
				retData = gen.RemoveNodeInJson (a[0],a[1]); 
				break;
		}
		case "StoreJsonKeys" : {  
				retData = gen.StoreJsonKeys (a[0],a[1]); 
				break;
		}
		case "StoreJsonArray" : {  
				retData = gen.StoreJsonArray (a[0],a[1],a[2]); 
				break;
		}
		case "StoreJsonPathCount" : {  
				retData = gen.StoreJsonPathCount (a[0],a[1],a[2]); 
				break;
		}
		case "StoreKeyCountForSpecificKey" : {  
				retData = gen.StoreKeyCountForSpecificKey (a[0],a[1],a[2]); 
				break;
		}
		case "StoreJsonKeyTotalCount" : {  
				retData = gen.StoreJsonKeyTotalCount (a[0],a[1]); 
				break;
		}
		case "StoreValueFromJson" : {  
				retData = gen.StoreValueFromJson (a[0],a[1],a[2]); 
				break;
		}
		case "ValidateJsonString" : {  
				retData = gen.ValidateJsonString (a[0]); 
				break;
		}
		case "StoreVariable" : {  
				//retData = gen.StoreVariable (key, data); 
				retData = gen.StoreVariable (a[0], a[1]); 
				break;
		}
	  
		}
		return retData;
	}

	public void invokeKeywordThruReflection(
			String actionName, 
			String argType, 
			String argList, 
			String delimitor ) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		Class<?> c = null;
		
		c = Class.forName("com.webdriverfw.Wrappers.General");	
		String[] a = argList.split(delimitor);
		String[] p1 = argType.split(",");
		Class<?>[] param_types = new Class<?>[a.length];
		for (int i=0; i<a.length;i++) {
			if (a[i].equals("vbempty")) {
				a[i] = "";
			}
			
			if (p1[i].equalsIgnoreCase("String")){
				//p[i] = String.class;
				param_types[i] = String.class;
			}else if (p1[i].equalsIgnoreCase("Integer")) {
				param_types[i] = Integer.TYPE;
			}
		}
		

		 
		Method m = c.getMethod(actionName, param_types);
		
		try {
			m.invoke(c.newInstance(), a);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.getCause().printStackTrace();
		}catch (Exception e) {

		    // generic exception handling
		    e.printStackTrace();
		}
		
		
		
	}

	@RequestMapping(value = "/1.0/LaunchQTP", method = RequestMethod.GET)
	public void launchQTP() {
		   try {
			      Runtime.getRuntime().exec( "wscript C:\\Users\\rajneesh.kumar\\Desktop\\callQTPService.vbs" );
			      System.out.println("Launching QTP");
			      ReadQtpCommands();
			      System.out.println("Reading Execution commands...");
			   }
			   catch( IOException e ) {
			      System.out.println(e);
			      System.exit(0);
			   }
    }
	
	
	public void ReadQtpCommands() {
		//qtpCommandList = new ArrayList<String>();
		qtpCommandList.add("SystemUtil.Run path");
		qtpCommandList.add("WaitFor(1)");
		qtpCommandList.add("WpfWindow(\"Micro Focus MyFlight Sample\").WpfEdit(\"agentName\").Set \"john\"");
		qtpCommandList.add("WaitFor(1)");
		qtpCommandList.add("WpfWindow(\"Micro Focus MyFlight Sample\").WpfEdit(\"password\").Set \"hp\"");
		qtpCommandList.add("WaitFor(1)");
		qtpCommandList.add("WpfWindow(\"Micro Focus MyFlight Sample\").WpfButton(\"OK\").Click");
		qtpCommandList.add("WaitFor(1)");
		qtpCommandList.add("WpfWindow(\"Micro Focus MyFlight Sample\").Close");
		qtpCommandList.add("WaitFor(1)");
		//qtpCommandList.add("exit");
	}

	@RequestMapping(value = "/1.0/executeQtpCommands", method = RequestMethod.POST)
	public String executeQtpCommands(
			@RequestHeader("lineNumber") int lineNumber) {
		if (setData==0 || qtpCommandList.size()<1 ) {
			ReadQtpCommands();
			setData =1;
		}
		
		if (lineNumber==0) {
			before = Instant.now();
		}
		
		if (lineNumber==qtpCommandList.size()-1) {
			after = Instant.now();
			long delta = Duration.between(before, after).toMillis();
			System.out.println("Call duration :"+delta);
			before = after;
		}
		return qtpCommandList.get(lineNumber);
	}

	
	@RequestMapping(value = "/1.0/printQtpStatus", method = RequestMethod.POST)
	public void printQtpStatus(
			@RequestHeader("status") int status) {
		
		logger.debug("QTP EXECUTION STATUS:"+status);
		System.out.println("QTP EXECUTION STATUS:"+status);
		//return qtpCommandList.get(status);
	}
	
}
