<?php 
	require "connect.php";

	$idchude= $_POST['idchude'];
	$query = "SELECT * FROM theloai WHERE IdChuDe = '$idchude'";
	$datatheloai = mysqli_query($conn,$query);

	class Theloai {
		function Theloai($idtheloai,$idchude,$tentheloai,$hinhtheloai){
			$this->IdTheLoai = $idtheloai;
			$this->IdChuDe = $idchude;
			$this->TenTheLoai = $tentheloai;
			$this->HinhTheLoai = $hinhtheloai;
		}
	}

	$arraytheloai = array();

	while ($row = mysqli_fetch_assoc($datatheloai)) {
		array_push($arraytheloai, new Theloai($row['IdTheLoai'],
											$row['IdChuDe'],
											$row['TenTheLoai'],
											$row['HinhTheLoai']));
	}
	echo json_encode($arraytheloai);
	

?>

