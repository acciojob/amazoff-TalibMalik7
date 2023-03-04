package com.driver;


import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
@Repository
public class OrderRepository {
    HashMap<String,Order> order_map;
    HashMap<String,DeliveryPartner> partner_map;
    HashMap<String,String> pair;
    HashMap<String, List<String>> pair_list;



    public OrderRepository(){
        this.order_map = new HashMap<>();
        this.partner_map = new HashMap<>();
        this.pair = new HashMap<>();
        this.pair_list = new HashMap<>();
    }

    //1
    public void addOrder(Order order){
        String name = order.getId();
        order_map.put(name,order);
    }
    //2
    public void addPartner(String  id){

        partner_map.put(id,new DeliveryPartner(id));

    }
    //3
    public void addOrderPartnerPair(String order, String partner){
        // this if is change extra - see it later
        if(order_map.containsKey(order) && partner_map.containsKey(partner)) {


            if (pair_list.containsKey(partner)) {
                pair_list.get(partner).add(order);
            } else {
                List<String> temp = new ArrayList<>();
                temp.add(order);
                pair_list.put(partner, temp);
            }
            DeliveryPartner obj = partner_map.get(partner);
            int no = pair_list.get(partner).size();
            obj.setNumberOfOrders(no);
            pair.put(order, partner);
        }
    }
    //4
    public Order getOrderById(String id){
        if(this.order_map.containsKey(id))
            return order_map.get(id);
        return null;
    }
    //5
    public DeliveryPartner getPartnerById(String id){
        if(partner_map.containsKey(id))
            return partner_map.get(id);
        return null;
    }
    //6
    public Integer getOrderCountByPartnerId(String id){
        Integer count = 0;
        if(partner_map.containsKey(id)){
            count = partner_map.get(id).getNumberOfOrders();
        }
        return  count;
    }
    //7
    public List<String> getOrdersByPartnerId(String id){
        if(pair_list.containsKey(id))
            return pair_list.get(id);
        return new ArrayList<String>();
    }
    //8
    public List<String> getAllOrders(){
        return new ArrayList<>(order_map.keySet());
    }
    //9
    public int getCountOfUnassignedOrders(){
        // change it if doest work
        //return order_map.size()- pair.size();
        Integer countOfOrders = 0;
        List<String> orders =  new ArrayList<>(order_map.keySet());
        for(String orderId: orders){
            if(!pair.containsKey(orderId)){
                countOfOrders += 1;
            }
        }
        return countOfOrders;
    }
    //10
    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String timee, String id){
        String [] time_ = timee.split(":");
        int time = Integer.parseInt(time_[0])*60 + Integer.parseInt(time_[1]);
        Integer count = 0;
        if(pair_list.containsKey(id)) {
            List<String> list = pair_list.get(id);

            for (String order : list) {
                if (order_map.containsKey(order)) {
                    Order obj = order_map.get(order);
                    if (time < obj.getDeliveryTime()) count+=1;
                }
            }
        }
        return count;
    }
    //11

    public String  getLastDeliveryTimeByPartnerId(String id){

        Integer temp=0;
        if(pair_list.containsKey(id)){
            for(String s : pair_list.get(id)) {
                if (order_map.containsKey(s)) {
                    Order order = order_map.get(s);
                    temp = Math.max(temp,order.getDeliveryTime());
                }
            }

        }
        Integer hour = temp/60;
        Integer minutes = temp%60;

        String hourInString = String.valueOf(hour);
        String minInString = String.valueOf(minutes);
        if(hourInString.length() == 1){
            hourInString = "0" + hourInString;
        }
        if(minInString.length() == 1){
            minInString = "0" + minInString;
        }

        return  hourInString + ":" + minInString;
    }
    // 12
    public void deletePartnerById(String id){
        if(this.pair_list.containsKey(id)){
            for(String s : pair_list.get(id)){
                if(pair.containsKey(s)){
                    pair.remove(s);
                }
            }
            pair_list.remove(id);
        }
        if(this.partner_map.containsKey(id)){
            this.partner_map.remove(id);
        }
    }
    public void deleteOrderById(String id){

        if(pair.containsKey(id)){
            String partner = pair.get(id);
            if(pair_list.containsKey(partner)){
                List<String> order_list = pair_list.get(partner);
                order_list.remove(id);
                pair_list.put(partner,order_list);
            }
            DeliveryPartner obj = partner_map.get(partner);
            obj.setNumberOfOrders(pair_list.get(partner).size());
            pair.remove(id);
        }
        // another one
        if(this.order_map.containsKey(id)){
            order_map.remove(id);
        }
    }


}