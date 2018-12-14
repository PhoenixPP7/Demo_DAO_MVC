package mvc.util;

import javax.servlet.http.HttpServletRequest;

public class BasePath {
	//工具类,用来返回"basePath";
	public static String getBasePath(HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		return basePath;
	}
}
