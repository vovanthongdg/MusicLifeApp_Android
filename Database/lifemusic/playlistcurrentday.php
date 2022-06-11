<?php 
	require "connect.php";

	$query = "SELECT DISTINCT * FROM playlist ORDER BY rand(".date("Ymd") . ") LIMIT 3";
	/**
	 * 
	 */
	class PlaylistToday
	{
		function PlaylistToday($idplaylist, $tenpl, $hinhnen, $hinhicon)
		{
			$this->IdPlayList = $idplaylist;
			$this->TenPL = $tenpl;
			$this->HinhNen = $hinhnen;
			$this->HinhIcon = $hinhicon;
		}
	}
	$arrayplaylisttoday = array();
	$data = mysqli_query($conn, $query);
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($arrayplaylisttoday, new PlaylistToday($row['IdPlayList'],
							$row['TenPL'],
							$row['HinhNen'],
							$row['HinhIcon']));
	}
	echo json_encode($arrayplaylisttoday);
?>