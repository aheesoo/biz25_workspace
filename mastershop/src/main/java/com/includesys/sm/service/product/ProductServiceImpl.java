package com.includesys.sm.service.product;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.includesys.sm.service.product.ProductService;
import com.includesys.sm.dto.PageHelper;
import com.includesys.sm.dto.product.Product;
import com.includesys.sm.dao.product.ProductDao;

@Service
public class ProductServiceImpl  implements ProductService{
	//service클래스의 역할 : 필요한 dao호출
	private static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	private ProductDao productDao;
	
	@Override
	public List<Product> getProductList(){
		return productDao.getProductList();
	}
	
	@Override
	public List<Product> getProductCodeList(String search_code){
		return productDao.getProductCodeList(search_code);
	}
	
	@Override
	public Product getProductCodeListView(String search_code){
		return productDao.getProductCodeListView(search_code);
	}
	
	@Override
	public Product getProductCodeSearch(String search_code){
		return productDao.getProductCodeSearch(search_code);
	}
	
	@Override
	public void registerProductCode(Product product){
		productDao.registerProductCode(product);
	}
	
	@Override
	public int updateProductCode(Product product){
		return productDao.updateProductCode(product);
	}
	 
} 
