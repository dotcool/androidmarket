<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Welcome extends CI_Controller {
	 function __construct(){
              parent::__construct();          
             } 

	public function index()
	{
		if( $this->session->userdata("admin")!="") 
		{
			$this->main();
		
		}else
		{
			$this->load->view('index');	
		}
	}
	

	function main()
	{
		if($this->session->userdata("admin")!="") 
		{
		$this->load->view('main');	
		}else
		{
		 $inname=$this->input->post('name');
		 $inpwds=$this->input->post('pwd');	
		 if($inname!="" && $inpwds!="")
		 {
		 $inpwd=md5(md5($inpwds));	
		 $this->load->model("Mhome");
		 $var=$this->Mhome->get_pwd($inname);
		 if(!empty($var)){			  
		 foreach($var as $row){
			if($inpwd==$row->pwd){
				$this->session->set_userdata("admin",$inname);
				$this->load->view('main'); 
				}else{					
				$this->index();	
				echo "<script>alert('用户名或者密码错误！');</script>";
				}			
			}
		 }else{		
		        $this->index();	
				echo "<script>alert('用户名不存在！');</script>";
				
			 } 
		 }
		 else{
			 $this->index();	
				echo "<script>alert('用户名和密码不能为空！');</script>";
		 }	 
		}
		 
		
    }

		   
    function apk(){
		$this->load->library('pagination');
		$this->load->model('Mhome');
		$this->db->where('type','2');
		$res=$this->db->get('apps'); 
		$config['first_tag_open'] = '<li>';
		$config['first_link']='首页';
		$config['first_tag_close'] = '</li>';
		$config['last_tag_open'] = '<li>';
		$config['last_link']='尾页';		
		$config['last_tag_close'] = '</li>';
		$config['next_tag_open'] = '<li>';
		$config['next_link'] = '下一页';
		$config['next_tag_close'] = '</li>';
		$config['prev_tag_open'] = '<li>';
		$config['prev_link'] = '上一页';
		$config['prev_tag_close'] = '</li>';
		$config['num_tag_open'] = '<li>';
		$config['num_tag_close'] = '</li>';
		$config['cur_tag_open'] = '<li class="dq"><a>';
        $config['cur_tag_close'] = '</a></li>';
		$config['base_url']='index.php/welcome/apk';		
		$config['total_rows']=$res->num_rows();
		$num=$this->input->post("num");
		if($num==""){
			$config['per_page'] = 20;
		}else{
			$config['per_page'] = $num;
		}		
		$data['nums']=$num;	
	    $this->pagination->initialize($config); 
		$data['apklist']=$this->Mhome->get_apklist($config['per_page'],$this->uri->segment(3));		
		$this->load->view('apk',$data); 
		}
		
	function mrp(){
		$this->load->library('pagination');
		$this->load->model('Mhome');
		$this->db->where('type','1');
		$res=$this->db->get('apps'); 
		$config['first_tag_open'] = '<li>';
		$config['first_link']='首页';
		$config['first_tag_close'] = '</li>';
		$config['last_tag_open'] = '<li>';
		$config['last_link']='尾页';		
		$config['last_tag_close'] = '</li>';
		$config['next_tag_open'] = '<li>';
		$config['next_link'] = '下一页';
		$config['next_tag_close'] = '</li>';
		$config['prev_tag_open'] = '<li>';
		$config['prev_link'] = '上一页';
		$config['prev_tag_close'] = '</li>';
		$config['num_tag_open'] = '<li>';
		$config['num_tag_close'] = '</li>';
		$config['cur_tag_open'] = '<li class="dq"><a>';
        $config['cur_tag_close'] = '</a></li>';
		$config['base_url']='index.php/welcome/mrp';		
		$config['total_rows']=$res->num_rows();
		$num=$this->input->post("num");
		if($num==""){
			$config['per_page'] = 20;
		}else{
			$config['per_page'] = $num;
		}		
		$data['nums']=$num;	
		
	    $this->pagination->initialize($config); 
		$data['apklist']=$this->Mhome->get_mrplist($config['per_page'],$this->uri->segment(3));		
		$this->load->view('mrp',$data); 
	
		}
    function apijk(){	
		$this->load->view('apijk'); 
		}
	function ad(){
		$this->load->library('pagination');
		$this->load->model('Mhome');		
		$res=$this->db->get('adlist'); 
		$config['first_tag_open'] = '<li>';
		$config['first_link']='首页';
		$config['first_tag_close'] = '</li>';
		$config['last_tag_open'] = '<li>';
		$config['last_link']='尾页';		
		$config['last_tag_close'] = '</li>';
		$config['next_tag_open'] = '<li>';
		$config['next_link'] = '下一页';
		$config['next_tag_close'] = '</li>';
		$config['prev_tag_open'] = '<li>';
		$config['prev_link'] = '上一页';
		$config['prev_tag_close'] = '</li>';
		$config['num_tag_open'] = '<li>';
		$config['num_tag_close'] = '</li>';
		$config['cur_tag_open'] = '<li class="dq"><a>';
        $config['cur_tag_close'] = '</a></li>';
		$config['base_url']='index.php/welcome/mrp';		
		$config['total_rows']=$res->num_rows();
		$num=$this->input->post("num");
		if($num==""){
			$config['per_page'] = 20;
		}else{
			$config['per_page'] = $num;
		}		
		$data['nums']=$num;	
		
	    $this->pagination->initialize($config); 
		$data['apklist']=$this->Mhome->get_adlist($config['per_page'],$this->uri->segment(3));		
		$this->load->view('ad',$data); 
	 
		}
	function addad(){
		 $ids=$this->input->post('ids');
		 $name=$this->input->post('name');
		 $img=$this->input->post('img');
		 $url=$this->input->post('url');
		 $data=array('ids'=>$ids,'name'=>$name,'img'=>$img,'url'=>$url);
		 $this->load->model("Mhome");
		 $this->Mhome->insert_ad($data);
		 redirect('index.php/welcome/ad','refresh');
		}
	function add(){
		 $id=$this->input->post('id');
		 $icon=$this->input->post('icon');	
		 $title=$this->input->post('title');
		 $des=$this->input->post('des');
		 $orderNum=$this->input->post('orderNum');
		 $type=$this->input->post('type');
		 if($type=='1'){
			  $pkg=$this->input->post('pkg').'.mrp';
		}else{
				$pkg=$this->input->post('pkg'); 
		} 		 
		 $oId=$this->input->post('oId');
		 $url=$this->input->post('url');
		 $size=$this->input->post('size');	
		 $fl=$this->input->post('fl');
		 $rjxj=$this->input->post('rjxj');
		 $bb=$this->input->post('bb');
		 $data=array('id'=>$id,'icon'=>$icon,'title'=>$title,'des'=>$des,'orderNum'=>$orderNum,'pkg'=>$pkg,'type'=>$type,'oId'=>$oId,'url'=>$url,'size'=>$size,'fl'=>$fl,'rjxj'=>$rjxj,'bb'=>$bb);
		 $this->load->model("Mhome");
		 $this->Mhome->insert_app($data);
		 if($type=='1'){
			 redirect('index.php/welcome/mrp','refresh');
			 }elseif($type=='2'){
				 redirect('index.php/welcome/apk','refresh');
				 }
		 
		}
	function del(){
		$ids=$this->uri->segment(3);
		$this->load->model("Mhome");
		$this->Mhome->del_apklist($ids);
		redirect('index.php/welcome/apk','refresh');
		}
	function delad(){
		$ids=$this->uri->segment(3);
		$this->load->model("Mhome");
		$this->Mhome->del_adlist($ids);
		redirect('index.php/welcome/ad','refresh');
		}
	function edit(){
		$ids=$this->uri->segment(3);
		$this->load->model("Mhome");
		$data['apklist']=$this->Mhome->get_apk($ids);
		$this->load->view('edit',$data); 
		}	
	function editad(){
		$ids=$this->uri->segment(3);
		$this->load->model("Mhome");
		$data['apklist']=$this->Mhome->get_ad($ids);
		$this->load->view('editad',$data); 
		}	
	function updata(){
		 $id=$this->input->post('id');
		 $icon=$this->input->post('icon');	
		 $title=$this->input->post('title');
		 $des=$this->input->post('des');
		 $orderNum=$this->input->post('orderNum');
		 
		 $type=$this->input->post('type');
		 
		 $pkg=$this->input->post('pkg'); 
		
		 $oId=$this->input->post('oId');
		 $url=$this->input->post('url');
		 $size=$this->input->post('size');	
		 $fl=$this->input->post('fl');
		 $rjxj=$this->input->post('rjxj');
		 $bb=$this->input->post('bb');
         $ids=$this->input->post('ids');
		 $this->load->model("Mhome");
		 $data=array('id'=>$id,'icon'=>$icon,'title'=>$title,'des'=>$des,'orderNum'=>$orderNum,'pkg'=>$pkg,'type'=>$type,'oId'=>$oId,'url'=>$url,'size'=>$size,'fl'=>$fl,'rjxj'=>$rjxj,'bb'=>$bb);
		 $this->Mhome->update_apklist($ids,$data);
		 if($type=='1')
		 {redirect('index.php/welcome/mrp','refresh');}
		 else
		 {redirect('index.php/welcome/apk','refresh');}
		 
		}
	function update_ad(){
		 $ids=$this->input->post('ids');
		 $name=$this->input->post('name');
		 $img=$this->input->post('img');
		 $url=$this->input->post('url');
		 $id=$this->input->post('id');
		 $data=array('ids'=>$ids,'name'=>$name,'img'=>$img,'url'=>$url);
		 $this->load->model("Mhome");
		 $this->Mhome->update_adlist($id,$data);
		 redirect('index.php/welcome/ad','refresh');
		}
		function unsession(){
           	$this->session->sess_destroy();
           	redirect('index.php/welcome/index','refresh');
        }
		function test($str){
		  $code = json_encode($str);
	 
	      return preg_replace("#\\\u([0-9a-f]+)#ie", "iconv('UCS-2','UTF-8', pack('H4', '\\1'))", $code);
			
		}
	    function api(){
			
		 $this->load->model("Mhome");
		 $arr=$this->Mhome->selectall();
		 echo '{"apps":'.stripslashes($this->test($arr)).',"code":200}';
		 $this->load->view('api'); 
		}
		function mes(){
		 $mes=$this->input->post('mes');		
		 $data=array('mes'=>$mes);
		 $this->load->model("Mhome");
		 $arr=$this->Mhome->updatames($data);
		 $this->apijk();
		}
			
		function apimes(){
		      $this->load->model("Mhome");
		      $var=$this->Mhome->selectmes();		      		 
			 echo '{"mes":'.stripslashes($this->test($var)).',"code":200}';
		     $this->load->view('api'); 
		}
			
        function apiad(){		 				
		 $this->load->model("Mhome");
		 $arr=$this->Mhome->selectallad();
		 echo '{"adlist":'.stripslashes($this->test($arr)).',"code":200}';
		 $this->load->view('apiad'); 
		}
        function cx(){
          $num=$this->input->post("num");
		  $this->load->model("Mhome");		 
		  $data['apklist']=$this->Mhome->selectcx($num);
		  $data['nums']=$num;
		  $this->load->view('apk',$data);
		}
        function cxmrp(){
          $num=$this->input->post("num");
		  $this->load->model("Mhome");		 
		  $data['apklist']=$this->Mhome->selectcxmrp($num);	
		  
		   $data['nums']=$num;	
		  $this->load->view('mrp',$data);
		}
		
        function cxad(){
          $num=$this->input->post("num");
		  $this->load->model("Mhome");		 
		  $data['apklist']=$this->Mhome->selectcxad($num);	
		  
		   $data['nums']=$num;	
		  $this->load->view('ad',$data);
		}
}
