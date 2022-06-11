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

	$arraycakhuc = array();
	if (isset($_POST['keyword'])) {
		$keyword = $_POST['keyword'];
		$query = "SELECT * FROM baihat WHERE lower (TenBaiHat) LIKE '%$keyword%'";
		$data = mysqli_query($conn,$query);
		while ($row = mysqli_fetch_assoc($data)) {
		array_push($arraycakhuc, new Baihat($row['IdBaiHat'],
											$row['TenBaiHat'],
											$row['HinhBaiHat'],
											$row['CaSi'],
											$row['Path'],
											$row['LuotThich']));
		}
		echo json_encode($arraycakhuc);
	}


?>

