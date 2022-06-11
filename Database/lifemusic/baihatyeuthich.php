<?php 
	require "connect.php";
	
	class Baihat {
		function Baihat($idbaihat,$tenbaihat,$hinhbaihat,$casi,$linkbaihat,$luotthich){
			$this->IdBaiHat = $idbaihat;
			$this->TenBaiHat = $tenbaihat;
			$this->HinhBaiHat = $hinhbaihat;
			$this->CaSi = $casi;
			$this->Path = $linkbaihat;
			$this->LuotThich = $luotthich;
		}
	}
	$arraycasi = array();
	$query = "SELECT DISTINCT * FROM baihat ORDER BY LuotThich DESC LIMIT 5";
	$data = mysqli_query($conn,$query);
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($arraycasi, new Baihat($row['IdBaiHat'],
											$row['TenBaiHat'],
											$row['HinhBaiHat'],
											$row['CaSi'],
											$row['Path'],
											$row['LuotThich']));
	}
	echo json_encode($arraycasi);

?>