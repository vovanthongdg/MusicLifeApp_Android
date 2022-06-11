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

	$arraylistsong = array();
	
	if (isset($_POST['idalbum'])) {
		$idalbum = $_POST['idalbum'];
		$query = "SELECT * FROM baihat WHERE FIND_IN_SET('$idalbum',IdAlbum)";
	}
	
	if (isset($_POST['idtheloai'])) {
		$idtheloai = $_POST['idtheloai'];
		$query = "SELECT * FROM baihat WHERE FIND_IN_SET('$idtheloai',IdTheLoai)";
	}

	if (isset($_POST['idplaylist'])) {
		$idplaylist = $_POST['idplaylist'];
		$query = "SELECT * FROM baihat WHERE FIND_IN_SET('$idplaylist',IdPlayList)";
	}

	if (isset($_POST['idquangcao'])) {
		$idquangcao = $_POST['idquangcao'];
		$queryquangcao = "SELECT * FROM quangcao WHERE IdQuangCao = '$idquangcao'";
		$dataquangcao = mysqli_query($conn,$queryquangcao);
		$rowquangcao = mysqli_fetch_assoc($dataquangcao);
		$id = $rowquangcao['IdBaiHat'];
		$query = "SELECT * FROM baihat WHERE IdBaiHat = '$id'";
	}


	$data = mysqli_query($conn,$query);
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($arraylistsong, new Baihat($row['IdBaiHat'],
											$row['TenBaiHat'],
											$row['HinhBaiHat'],
											$row['CaSi'],
											$row['Path'],
											$row['LuotThich']));
	}
	echo json_encode($arraylistsong);
	
?>