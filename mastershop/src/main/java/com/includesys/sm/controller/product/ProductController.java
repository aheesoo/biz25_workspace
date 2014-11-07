package com.includesys.sm.controller.product;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.includesys.sm.dto.product.Product;
import com.includesys.sm.service.manager.AdminService;
import com.includesys.sm.service.manager.CMSMenuService;
import com.includesys.sm.service.manager.LoginService;
import com.includesys.sm.service.product.ProductService;

@Controller("product.ProductController")
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private AdminService adminService; 
	
	@Autowired
   private CMSMenuService cmsMenuService;	
	
	@Autowired
	private ProductService productService;	
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping(value="/productList.do")
	public ModelAndView getList(){
		System.out.println("[CALL Controller] productList");
		
		List<Product> productList = productService.getProductList();
		//List<Product> productListCount = productService.getProductListCount();
		//생성자에서 받은 문자열은 뷰이름을 의미
		ModelAndView mv = new ModelAndView("product/productList");
		mv.addObject("productList", productList);
		//mv.addObject("productListCount", productListCount);
		return mv;
	}
	
	/**
	 * 수정페이지 요청
	 * @return
	 */
	@RequestMapping(value="/productModify.do")
	public ModelAndView getProductModify(){
		System.out.println("[CALL Controller] productList");
		boolean skip_process = true;
		List<Product> productList_depth1 = null;
		List<Product> productList_depth2 = null;
		try{		
			productList_depth1 = productService.getProductCodeList("0000");
		}catch(Exception e){
			skip_process = false;
			e.printStackTrace();
		}
		if(skip_process){
			try{
			
				productList_depth2 = productService.getProductCodeList("A");
			}catch(Exception e){
				skip_process = false;
				e.printStackTrace();
			}
		}
		
		
		//생성자에서 받은 문자열은 뷰이름을 의미
		ModelAndView mv = new ModelAndView("product/productModify");
		mv.addObject("productList_depth1", productList_depth1);
		mv.addObject("productList_depth2", productList_depth2);
		return mv;
	}
	
	/**
	 * 품목 코드 조회
	 * @param map
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/searchCodeList.do")
	public JSONObject getSearchCodeList(@RequestParam Map<String, String> map, HttpServletRequest request){
		System.out.println("[CALL Product Controller] getSearchCodeList");
		
		String req_search_code = map.get("search_code") == null ? "" : map.get("search_code").toString();
		String req_search_type = map.get("search_type") == null ? "" : map.get("search_type").toString();
		
		//System.out.println("req_search_type : " + req_search_type);
		
		JSONObject jsonObj = new JSONObject();	//object
		
		
		if(req_search_type.equals("list")){
			try{
				List<Product> productList_depth = productService.getProductCodeList(req_search_code);
				JSONArray jsonArr 	= new JSONArray();	//list
				if(productList_depth.size()>0){
				
					for(Product item:productList_depth){
						JSONObject jsonItem	= new JSONObject();	// json code_list
						
						jsonItem.put("pk_product_code"		, item.getPk_product_code()		);
						jsonItem.put("fd_up_code"				, item.getFd_up_code() 			);
						jsonItem.put("fd_code_name"			, item.getFd_code_name() 		);
						jsonItem.put("fd_point_level_5"		, item.getFd_point_level_5() 	);
						jsonItem.put("fd_point_level_10"		, item.getFd_point_level_10() 	);

						jsonArr.add(jsonItem);
					}	
				}
				jsonObj.put("list"		, jsonArr);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(req_search_type.equals("view")){
			try{
				
				Product product = productService.getProductCodeListView(req_search_code);
				
				if(product !=null){
					jsonObj.put("pk_product_code"		, product.getPk_product_code()		);
					jsonObj.put("fd_up_code"				, product.getFd_up_code() 			);
					jsonObj.put("fd_code_name"			, product.getFd_code_name() 		);
					jsonObj.put("fd_point_level_5"		, product.getFd_point_level_5() 	);
					jsonObj.put("fd_point_level_10"	, product.getFd_point_level_10() 	);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
		System.out.println("jsonObj : " + jsonObj);
		return jsonObj;
	}
	
	/**
	 * 품목 depth 등록
	 * @param map
	 * @param request
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/registerDepth.do")
	public JSONObject registerDepth(@RequestParam Map<String, String> map, HttpServletRequest request){
		System.out.println("[CALL Product Controller] registerDepth");
		
		String pk_product_code = "";
		String fd_code_name 	= map.get("code_name") == null ? "" : map.get("code_name").toString();		// depth 타이틀
		String fd_up_code 		= map.get("up_code") == null ? "" : map.get("up_code").toString();			// depth 포지션		
		String result_code   		= "200";		
		boolean process 		= true;
		char[] db_depth 			= null;		
		JSONObject jsonObj = new JSONObject();	//object

		System.out.println("fd_code_name : " + fd_code_name);
		System.out.println("fd_up_code : " + fd_up_code);
		
		try{
			
			Product product = productService.getProductCodeSearch(fd_up_code);
			
			if(product !=null){
				db_depth =  product.getPk_product_code().toCharArray();
			}
		}catch(Exception e){
			process = false;
			e.printStackTrace();
		}
		
		if(process){
			try{
				char result_depth1 ='\u0000';
				char next_depth1 	= '\u0000';
				if(db_depth !=null){
					if(db_depth.length==1){
						result_depth1 = db_depth[0];					
					}else if(db_depth.length==2){
						result_depth1 = db_depth[1];					
					}else{
						process=false;				
					}
					next_depth1 = (char) (result_depth1+1);
				}else{
					next_depth1 = (char) 'A';
				}			

				if(fd_up_code.equals("0000")){
					pk_product_code = String.valueOf(next_depth1);
				}else{
					pk_product_code = fd_up_code + String.valueOf(next_depth1);
				}
				
				if(next_depth1== (char)(90)){
					process=false;
					result_code= "300";
				}
			}catch(Exception e){
				process=false;
				e.printStackTrace();
			}
			
		}
		
		if(process){
			try{
				Product product = new Product();				
				product.setPk_product_code(pk_product_code);
				product.setFd_code_name(fd_code_name);
				product.setFd_up_code(fd_up_code);
				product.setFd_point_level_5("0");
				product.setFd_point_level_10("0");
				
				
				productService.registerProductCode(product);
						
				
			}catch(Exception e){
				result_code = "301";
				e.printStackTrace();
			}
		}
		jsonObj.put("code_name", fd_code_name);
		jsonObj.put("product_code", pk_product_code);
		jsonObj.put("result_code", result_code);
		
		System.out.println("jsonObj : " + jsonObj);
		return jsonObj;
	}
	
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/updateDepth.do")
	public JSONObject updateDepth(@RequestParam Map<String, String> map, HttpServletRequest request){
		System.out.println("[CALL Product Controller] updateDepth");
		
		String pk_product_code 	= map.get("product_code") == null ? "" : map.get("product_code").toString();
		String fd_code_name 		= map.get("code_name") == null ? "" : map.get("code_name").toString();		// depth 타이틀
		String fd_up_code 			= map.get("up_code") == null ? "" : map.get("up_code").toString();				// depth 포지션
				
		String result_code   		= "200";		
		boolean process 		= true;
		
		System.out.println("pk_product_code : " + pk_product_code);
		System.out.println("fd_code_name : " + fd_code_name);
		System.out.println("fd_up_code : " + fd_up_code);
		
		JSONObject jsonObj = new JSONObject();	//object
		
		if(process){
			try{
				Product product = new Product();
				
				product.setPk_product_code(pk_product_code);
				product.setFd_code_name(fd_code_name);
				product.setFd_up_code(fd_up_code);
				
				int result = productService.updateProductCode(product);
				//System.out.println("result : " + result);
				if(result<=0){
					result_code = "311";
				}
				
			}catch(Exception e){
				result_code = "301";
				e.printStackTrace();
			}
		}
		
		jsonObj.put("code_name", fd_code_name);
		jsonObj.put("product_code", pk_product_code);
		jsonObj.put("result_code", result_code);
		
		System.out.println("jsonObj : " + jsonObj);
		return jsonObj;
	}
	
	
	/**
	 * 품목의 기본 포인트 수정
	 * @param map
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/updateProduct.do")
	public JSONObject updateProduct(@RequestParam Map<String, String> map, HttpServletRequest request){		
		System.out.println("[CALL Product Controller] updateProduct");
		
		String product_code 			= map.get("product_code") 				== null ? "" : map.get("product_code").toString();				// 제품 코드
		String base_point_level_5 	= map.get("base_point_level_5") 		== null ? "" : map.get("base_point_level_5").toString();		// 기본 일반 포인트
		String base_point_level_10 	= map.get("base_point_level_10") 	== null ? "" : map.get("base_point_level_10").toString();		// 기본 써포터즈 포인트
		String up_code					= map.get("up_code") 						== null ? "" : map.get("up_code").toString();						// 기본 써포터즈 포인트
		String result_code   				= "200";		
		
		boolean process 		= true;
		JSONObject jsonObj = new JSONObject();	//object
		
		if(product_code.equals("")){
			result_code = "301";
			process =false;
		}else if(base_point_level_5.equals("")){
			result_code = "302";
			process =false;
		}else if(base_point_level_10.equals("")){
			result_code = "303";
			process =false;
		}else if(up_code.equals("")){
			result_code = "304";
			process =false;
		}
		
		if(process){
			try{
				Product product = new Product();
				
				product.setPk_product_code(product_code);
				product.setFd_point_level_5(base_point_level_5);
				product.setFd_point_level_10(base_point_level_10);
				product.setFd_up_code(up_code);
				
				int result = productService.updateProductCode(product);
				if(result<=0){
					result_code = "311";
				}
			}catch(Exception e){
				result_code = "310";
				process =false;
			}
		}
		
		jsonObj.put("result_code", result_code);
		return jsonObj;
		
	}
	
}

