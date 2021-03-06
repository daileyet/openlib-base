package com.openthinks.easyweb;

import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import com.openthinks.easyweb.annotation.process.WebProcesser;
import com.openthinks.easyweb.annotation.process.objects.WebContainer;
import com.openthinks.easyweb.annotation.process.objects.WebMethod;
import com.openthinks.easyweb.context.RequestSuffix;
import com.openthinks.easyweb.context.WebContexts;
import com.openthinks.easyweb.exception.CheckNoPassException;

public class WebUtils {

	public static WebMethod getWebMethod(HttpServletRequest req) {
		String path = req.getRequestURI();
		String mappingPath = WebUtils.convertToRequestMapingPath(path,
				WebContexts.get().getWebConfigure().getRequestSuffix());
		WebContainer container = WebContexts.get().getWebContainer();
		WebMethod webMethod = container.lookup(mappingPath);
		return webMethod;
	}

	@SuppressWarnings("deprecation")
	public static String encode(Object object) {
		String retval = null;
		if (object != null) {
			retval = URLEncoder.encode(object.toString());
		}
		return retval;
	}

	@SuppressWarnings("deprecation")
	public static String decode(Object object) {
		String retval = null;
		if (object != null) {
			retval = URLDecoder.decode(object.toString());
		}
		return retval;
	}

	/**
	 * Used for web path
	 * @param fullPath
	 * @param relativePath
	 * @return
	 */
	public static String contactPath(String fullPath, String relativePath) {
		// TODO check parameter can not be null
		String rp = relativePath;
		while (rp.indexOf("/") == 0) {
			rp = rp.substring(1);
		}
		String fp = fullPath;
		if (fp.lastIndexOf("/") < 1) {
			if ("/".equals(fp.trim())) {// Fix Bug for root path is "/" and
										// controller's root path is default
				fp = "";
			}
		} else {
			while (fp.lastIndexOf("/") == fp.length() - 1) {
				fp = fp.substring(0, fp.length() - 1);
			}
		}
		return fp + "/" + rp;
		
	}
	
	
	/**
	 * Used for local file path
	 * @param fullPath
	 * @param relativePath
	 * @return
	 */
	public static String contactFilePath(String fullPath, String relativePath) {
		// TODO check parameter can not be null
		String rp = relativePath;
		while (rp.indexOf(WebProcesser.PATH_SPLITER) == 0) {
			rp = rp.substring(1);
		}
		String fp = fullPath;
		if (fp.lastIndexOf(WebProcesser.PATH_SPLITER) < 1) {
			if (WebProcesser.PATH_SPLITER.equals(fp.trim())) {// Fix Bug for root path is "/" and
										// controller's root path is default
				fp = "";
			}
		} else {
			while (fp.lastIndexOf(WebProcesser.PATH_SPLITER) == fp.length() - 1) {
				fp = fp.substring(0, fp.length() - 1);
			}
		}
		return fp + WebProcesser.PATH_SPLITER + rp;
		
	}

	public static String convertToRequestMapingPath(String requestURI,
			RequestSuffix suffix) {
		for (String option : suffix.options()) {
			String URI = requestURI.toLowerCase();
			if (!URI.endsWith(option)) {
				continue;
			}
			int suffixIndex = URI.lastIndexOf(option);
			return requestURI.substring(0, suffixIndex);
		}
		return requestURI;
	}

	public static String convertToRequestURI(String requestMappingPath,
			RequestSuffix suffix) {
		String requestURI = requestMappingPath + suffix.options()[0];
		return requestURI;
	}

	public static void checkParamNumbers(Class<?>[] paramTypes, Object[] args)
			throws CheckNoPassException {
		if (paramTypes != null && args != null
				&& paramTypes.length == args.length) {
			return;// PASS
		} else if (paramTypes == null && args == null) {
			return;// PASS
		}
		throw new CheckNoPassException(WebUtils.class, "checkParamNumbers");
	}

	public static void checkParamTypes(Class<?>[] paramTypes, Object[] args)
			throws CheckNoPassException {
		if (paramTypes != null && args != null) {
			for (int index = 0; index < paramTypes.length; index++) {
				try {
					if (args[index] == null
							|| paramTypes[index] == args[index].getClass()) {
						continue; // PASS
					}
					if (args[index] == null
							|| paramTypes[index].isAssignableFrom(args[index]
									.getClass())) {
						continue; // PASS
					}
				} catch (Exception e) {
					throw new CheckNoPassException(WebUtils.class,
							"checkParamTypes", e);
				}
				throw new CheckNoPassException(WebUtils.class,
						"checkParamTypes");
			}
			return;// PASS
		}
		throw new CheckNoPassException(WebUtils.class, "checkParamTypes");
	}

}
