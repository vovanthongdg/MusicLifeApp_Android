<?php  
	require "connect.php";
	$query = "SELECT quangcao.IdQuangCao, quangcao.HinhAnh, quangcao.NoiDung, quangcao.IdBaiHat, baihat.TenBaiHat, baihat.HinhBaiHat FROM `baihat` INNER JOIN `quangcao`ON quangcao.IdBaiHat = baihat.IdBaiHat WHERE quangcao.IdBaiHat = baihat.IdBaiHat";
	$data = mysqli_query($conn,$query);
	/**
	 * 
	 */
	class Quangcao
	{
		
		function Quangcao($idquangcao,$hinhanh,$noidung,$idbaihat,$tenbaihat,$hinhbaihat) {
			$this->IdQuangCao=$idquangcao;
			$this->HinhAnh=$hinhanh;
			$this->NoiDung=$noidung;
			$this->IdBaiHat=$idbaihat;
			$this->TenBaiHat=$tenbaihat;
			$this->HinhBaiHat=$hinhbaihat;
		}
	}
	$mangquangcao = array();
	while ($row = mysqli_fetch_assoc($data)) {
			array_push($mangquangcao, new Quangcao($row['IdQuangCao']
							,$row['HinhAnh']
							,$row['NoiDung']
							,$row['IdBaiHat']
							,$row['TenBaiHat']
							,$row['HinhBaiHat']));
	}
	echo json_encode($mangquangcao);

?>