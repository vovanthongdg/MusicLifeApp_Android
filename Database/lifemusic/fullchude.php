<?php 
	require "connect.php";

	$query = "SELECT * FROM chude";
	$datachude = mysqli_query($conn,$query);

	class Chude {
		function Chude($idchude,$tenchude,$hinhchude){
			$this->IdChuDe = $idchude;
			$this->TenChuDe = $tenchude;
			$this->HinhChuDe = $hinhchude;
		}
	}

	$arraychude = array();

	while ($row = mysqli_fetch_assoc($datachude)) {
		array_push($arraychude, new Chude($row['IdChuDe'],
											$row['TenChuDe'],
											$row['HinhChuDe']));
	}
	echo json_encode($arraychude);
	

?>

