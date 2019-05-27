<?php
	
	class DbOperations{

		private $con;

		function __construct(){

			require_once dirname(__FILE__).'/DbConnect.php';

			$db = new DbConnect();

			$this->con = $db->connect();
		}

		/* Create */

		public function createUser($username, $pass, $email) {

			if ($this->isUserExist($username, $email)){
				return 0;
			}else{

				$password = md5($pass);

				$stmt = $this->con->prepare("INSERT INTO `User` (`id`, `username`, `password`, `email`) VALUES (NULL, ?, ?, ?);");

				$stmt->bind_param("sss",$username,$password, $email);

				if ($stmt->execute()) {
					return 1;
				}
				else{
					return 2;
				}
			}

		}

		public function userLogin($username, $pass){

			$password = md5($pass);
			$stmt = $this->con->prepare("SELECT id from User WHERE username = ? AND password = ?");
			$stmt->bind_param("ss", $username, $password);
			$stmt->execute();
			$stmt->store_result();
			return $stmt->num_rows > 0;
		}

		public function shopLogin($id, $pass){

			$stmt = $this->con->prepare("SELECT id from Shops WHERE id = ? AND password = ?");
			$stmt->bind_param("ss", $id, $pass);
			$stmt->execute();
			$stmt->store_result();
			return $stmt->num_rows > 0;

		}



		public function getUserByUsername($username){
			$stmt = $this->con->prepare("SELECT * from User WHERE username = ?");
			$stmt->bind_param("s", $username);
			$stmt->execute();
			return $stmt->get_result()->fetch_assoc();
		}

		public function getUserByShopId($id){
			$stmt = $this->con->prepare("SELECT * from Shops WHERE id = ?");
			$stmt->bind_param("s", $id);
			$stmt->execute();
			return $stmt->get_result()->fetch_assoc();
		}

		private function isUserExist($username, $email){

			$stmt = $this->con->prepare("SELECT id FROM User WHERE username = ? OR email = ?");
			$stmt->bind_param("ss", $username, $email);
			$stmt->execute();
			$stmt->store_result();

			return $stmt->num_rows > 0;

		}

		public function createShop($id, $password, $shopName, $seatCapacity, $openingTime, $closingTime, $leaveDay, $ownerName, $contactNumber, $pricing){

			if ($this->isShopExist($id)){
				return 0;
			}else{
				$stmt = $this->con->prepare("INSERT INTO `Shops` (`id`, `shopName`, `seatCapacity`, `openingTime`, `closingTime`, `leaveDays`, `ownerName`, `contactNumber`, `Pricing`, `password`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

				$stmt->bind_param("ssssssssss", $id, $shopName, $seatCapacity, $openingTime, $closingTime, $leaveDay, $ownerName, $contactNumber, $pricing, $password);

				if($stmt->execute()){
					return 1;
				}else{
					return 2;
				}

			}

		}

		private function isShopExist($id){

			$stmt = $this->con->prepare("SELECT id FROM Shops WHERE id = ?");
			$stmt->bind_param("s", $id);
			$stmt->execute();
			$stmt->store_result();

			return $stmt->num_rows > 0;

		}
	}