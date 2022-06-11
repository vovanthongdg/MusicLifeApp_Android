<?php

   if($_SERVER['REQUEST_METHOD']=='POST'){

       include_once("connect.php");
       
        $username = $_POST['username'];
 		$password = $_POST['password'];
 	
	 if( $username == '' || $password == '' ){
	        echo json_encode(array( "status" => "false","message" => "Vui Lòng nhập username và mật khẩu!!!") );
	 }else{
	 	$query= "SELECT * FROM users WHERE username='$username' AND password='$password'";
	        $result= mysqli_query($conn, $query);
		 
	        if(mysqli_num_rows($result) > 0){  
	         $query= "SELECT * FROM users WHERE username='$username' AND password='$password'";
	                    $result= mysqli_query($conn, $query);
		             	$emparray = array();
	                    if(mysqli_num_rows($result) > 0){  
	                     	while ($row = mysqli_fetch_assoc($result)) {
                                $emparray[] = $row;
                            }
	                     }
	           echo json_encode(array( "status" => "true","message" => "Đăng nhập thành công!", "data" => $emparray) );
	        }else{ 
	        	echo json_encode(array( "status" => "false","message" => "Sai username hoặc mật khẩu!") );
	        }
	         mysqli_close($conn);
	 }
	} else{
			echo json_encode(array( "status" => "false","message" => "Đã xảy ra lỗi, vui lòng thử lại!") );
	}
?>