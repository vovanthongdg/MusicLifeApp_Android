<?php

   if($_SERVER['REQUEST_METHOD']=='POST'){

       include_once("connect.php");
       
        $email = $_POST['email'];
 		$username = $_POST['username'];
 		$password = $_POST['password'];
  
	 if($email == '' || $username == '' || $password == ''){
	        echo json_encode(array( "status" => "false","message" => "Vui Lòng nhập các trường còn trống!") );
	 }else{
			 
	        $query= "SELECT * FROM users WHERE username='$username'";
	        $result= mysqli_query($conn, $query);
		 
	        if(mysqli_num_rows($result) > 0){  
	           echo json_encode(array( "status" => "false","message" => "Username đã sử dụng!") );
	        }else{ 
		 	 $query = "INSERT INTO users (email,username,password) VALUES ('$email','$username','$password')";
			 if(mysqli_query($conn,$query)){
			    
			     $query= "SELECT * FROM users WHERE username='$username'";
	                    $result= mysqli_query($conn, $query);
		             	$emparray = array();
	                    if(mysqli_num_rows($result) > 0){  
	                    	while ($row = mysqli_fetch_assoc($result)) {
                                $emparray[] = $row;
                          	}
	                    }
			    echo json_encode(array( "status" => "true","message" => "Đăng ký thành công!" , "data" => $emparray) );
		 	 }else{
		 		 echo json_encode(array( "status" => "false","message" => "Đã xảy ra lỗi, vui lòng thử lại!!!") );
		 	}
	    }
	            mysqli_close($conn);
	 }
     } else{
			echo json_encode(array( "status" => "false","message" => "Đã xảy ra lỗi, vui lòng thử lại!!!!") );
	}
 
 ?>