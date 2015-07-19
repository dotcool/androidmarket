<?php
 class Mhome   extends  CI_Model{
  
 	function __construct(){
 	
 		parent::__construct();
 	
 	}
 	
   function get_pwd($name){
   $query = $this->db->query('select pwd from yh where name="'.$name.'"');
   $this->db->close();
   return $query->result();
   }
   function insert_app($data){	   
   $this->db->insert('apps', $data);
   }
   function insert_ad($data){	   
   $this->db->insert('adlist', $data);
   }
   function get_apklist($num,$offset){
   $this->db->where('type','2');
   $this->db->order_by('orderNum','DESC');
   $query = $this->db->get('apps',$num,$offset);
   $this->db->close();
   return $query->result();
   }
   function get_mrplist($num,$offset){
   $this->db->where('type','1');
   $this->db->order_by('orderNum','DESC');
   $query = $this->db->get('apps',$num,$offset);
   $this->db->close();
   return $query->result();
   }
   function get_adlist($num,$offset){
   $this->db->order_by('ids','ASC');
   $query = $this->db->get('adlist',$num,$offset);
   $this->db->close();
   return $query->result();
   }
   function get_ad($id){
   $query = $this->db->query('select * from adlist where id='.$id.'');
   $this->db->close();
   return $query->result();
   }
   function del_apklist($ids){
   $query = $this->db->query('delete from apps where ids='.$ids.' ');
   $this->db->close();
   }
   function del_adlist($ids){
   $query = $this->db->query('delete from adlist where id='.$ids.' ');
   $this->db->close();
   }
   function update_apklist($ids,$data){
   $this->db->where('ids',$ids);
   $this->db->update('apps', $data);
   }
   function update_adlist($ids,$data){
   $this->db->where('id',$ids);
   $this->db->update('adlist', $data);
   }
   function get_apk($ids){
   $query = $this->db->query('select * from apps where ids='.$ids.' ');
   $this->db->close();
   return $query->result();
   }
   function selectall(){
	    $query = $this->db->query('select id,icon,title,des,orderNum,pkg,type,oId,url,size,fl,rjxj,bb from apps ORDER BY orderNum DESC ');
   $this->db->close();
   return $query->result_array();
	   }
   function selectallad(){
	    $query = $this->db->query('select ids as id,name,img,url from adlist ORDER BY ids ASC');
   $this->db->close();
   return $query->result_array();
	   }
   function updatames($data){
	   $this->db->where('id',1);
       $this->db->update('mes', $data);
	   }
   function selectmes(){
		$query = $this->db->get('mes');
   $this->db->close();
   return $query->result();
		}
   function selectcx($num){
   $query = $this->db->query('select * from apps where type="2" ORDER BY orderNum DESC limit '.$num.' ');
   $this->db->close();
   return $query->result();
		}
   function selectcxmrp($num){
   $query = $this->db->query('select * from apps where type="1" ORDER BY orderNum DESC limit '.$num.' ');
   $this->db->close();
   return $query->result();
		}
   function selectcxad($num){
   $query = $this->db->query('select * from adlist  ORDER BY ids ASC limit '.$num.' ');
   $this->db->close();
   return $query->result();
		}
 }

 