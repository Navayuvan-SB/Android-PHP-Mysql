<?php

	
	require_once '../includes/DbOperations.php';

	$response = array();

	if ($_SERVER['REQUEST_METHOD'] == 'POST'){

		if (isset($_POST['id'])){
			
			$db = new DbOperations();

			if ($db->getShopForAdmin($_POST['id']) != false){

				$user = $db->getShopForAdmin($_POST['id']);

				$response['error'] = false;
				$response['id'] = $user['id'];
				$response['status'] = $user['status'];
				$response['shopName'] = $user['shopName'];
				$response['bookings'] = $user['bookings'];
			}
			else{
				$response['error'] = true;
				$response['message'] = "Invalid Shop Name";
			}
		}
		else{
			$response['error'] = true;
			$response['message'] = "Required fields are missing";
		}
	}

	echo json_encode($response);