package com.lanson.oa.service;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanson.eoa.dao.OrderInfoDAO;
import com.lanson.oa.dao.SupOderWorkDAO;
import com.lanson.oa.pojo.OrderInfo;
import com.lanson.oa.pojo.OrderInfoJson;
import com.lanson.oa.pojo.SupOrderWork;

@Service
public class CommentService {
	@Autowired
	private OrderInfoDAO  dao;
	
	@Autowired
	private SupOderWorkDAO sDao;
	

   
	public String  orderList(int supplyId,String orderNo,int start,int end){
		if(orderNo==null){
			orderNo="";
		}
		String searchText = new StringBuilder("%").append(orderNo).append("%").toString();
		   List<OrderInfo> list=new ArrayList<OrderInfo>();
	    	list=dao.selectCommentBySupplyId(supplyId,searchText,start+1,start+end);
	    	OrderInfoJson orderInfoJson=new OrderInfoJson();
	    	orderInfoJson.setRoot(list);

	    	orderInfoJson.setCount(list.get(0).getCount());
	   	    JSONObject  arr=JSONObject.fromObject(orderInfoJson);
	   	    return arr.toString();
	}
	
	public  int saveOrUpdate(SupOrderWork supOrderWork){
			return sDao.commentSaveOrUpdate(supOrderWork);
	}

	
	
	
}
