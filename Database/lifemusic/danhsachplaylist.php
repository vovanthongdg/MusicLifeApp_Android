<?php 
	require "connect.php";

	$query = "SELECT * FROM playlist";
	$data = mysqli_query($conn,$query);

	class Danhsachplaylist {
		function Danhsachplaylist($idplaylist,$tenplaylist,$hinhnen,$hinhicon){
			$this->IdPlayList = $idplaylist;
			$this->TenPL = $tenplaylist;
			$this->HinhNen = $hinhnen;
			$this->HinhIcon = $hinhicon;
		}
	}

	$arrayplaylist = array();

	while ($row = mysqli_fetch_assoc($data)) {
		array_push($arrayplaylist, new Danhsachplaylist($row['IdPlayList'],
											$row['TenPL'],
											$row['HinhNen'],
											$row['HinhIcon']));
	}
	echo json_encode($arrayplaylist);
	

?>

