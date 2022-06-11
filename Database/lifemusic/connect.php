<?php 
	$hostname = "localhost";
	$username = "root";
	$password = "";
	$databasename = "id13641014_lovemusic";

	$conn = mysqli_connect($hostname,$username,$password,$databasename);
	mysqli_query($conn,"SET NAMES 'utf8'");
?>