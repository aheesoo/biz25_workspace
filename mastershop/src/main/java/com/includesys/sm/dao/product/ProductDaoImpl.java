package com.includesys.sm.dao.product;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.includesys.sm.dto.product.Product;

/*
 * spring이 SqlSession의 라이프싸이클을 관리하고, 트랜잭션을 관리한다.
 */
@Repository
public class ProductDaoImpl implements ProductDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<Product> getProductList(){
		return sqlSession.selectList("Product.getProductList");	
	}
	
	@Override
	public List<Product> getProductCodeList(String search_code){
		return sqlSession.selectList("Product.getProductCodeList", search_code);	
	}
	
	@Override
	public Product getProductCodeListView(String search_code){		
		return sqlSession.selectOne("Product.getProductCodeListView", search_code);	
	}
	
	@Override
	public Product getProductCodeSearch(String search_code){		
		return sqlSession.selectOne("Product.getProductCodeSearch", search_code);	
	}

	@Override
	public void registerProductCode(Product product) {
		sqlSession.insert("Product.registerProductCode", product);
		
	}

	@Override
	public int updateProductCode(Product product) {
		return sqlSession.update("Product.UpdateProductCode", product);
		
	}
	
}
