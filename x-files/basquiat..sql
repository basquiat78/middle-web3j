SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS ICO_TRANSACTION;
DROP TABLE IF EXISTS ICO_USER;
DROP TABLE IF EXISTS ICO_BALANCE;
DROP TABLE IF EXISTS ICO_WALLET_BACKUP;


/* Create Tables */

-- TRANSACTION
CREATE TABLE ICO_TRANSACTION
(
	-- transaction_hash
	transaction_hash varchar(300) NOT NULL COMMENT 'transaction_hash : transaction_hash',
	-- block_hash
	block_hash varchar(300) NOT NULL COMMENT 'block_hash : block_hash',
	-- block_number
	block_number varchar(300) NOT NULL COMMENT 'block_number : block_number',
	-- from_address
	from_address varchar(300) NOT NULL COMMENT 'from_address : from_address',
	-- to_address
	to_address varchar(300) NOT NULL COMMENT 'to_address : to_address',
	-- contract_address
	contract_address varchar(300) COMMENT 'contract_address : contract_address',
	-- value
	value varchar(300) COMMENT 'value : value',
	-- contractValue
	contractValue varchar(300) COMMENT 'contractValue : contractValue',
	-- NORMAL | CONTRACT
	transaction_type varchar(30) COMMENT 'transaction_type : NORMAL | CONTRACT',
	-- input
	input varchar(300) COMMENT 'input : input',
	-- 트랜잭션의 timestamp
	time_stamp varchar(300) COMMENT 'time_stamp : 트랜잭션의 timestamp',
	-- regDttm
	regDttm date COMMENT 'regDttm : regDttm',
	PRIMARY KEY (transaction_hash)
) COMMENT = 'TRANSACTION';


-- USER
CREATE TABLE ICO_USER
(
	id bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
	-- user_id
	user_id varchar(300) NOT NULL COMMENT 'user_id : user_id',
	-- eth_address
	eth_address varchar(300) NOT NULL COMMENT 'eth_address : eth_address',
	regDttm date NOT NULL COMMENT 'regDttm',
	PRIMARY KEY (id)
) COMMENT = 'USER' DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

-- BALANCE
CREATE TABLE ICO_BALANCE
(
	-- user_id
	user_id varchar(300) NOT NULL COMMENT 'user_id : user_id',
	-- eth_address
	account_address varchar(300) NOT NULL COMMENT 'account_address : account_address',
	eth_balance decimal(65,0) COMMENT 'eth_balance : eth_balance',
	token_balance decimal(65,0) COMMENT 'token_balance : token_balance',
	PRIMARY KEY (user_id)
) COMMENT = 'BALANCE' DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

-- Wallet Backup
CREATE TABLE ICO_WALLET_BACKUP
(
	-- user_id
	user_id varchar(300) NOT NULL COMMENT 'user_id : user_id',
	-- file_name
	file_name varchar(300) NOT NULL COMMENT 'file_name : file_name',
	wallet_contents json NOT NULL COMMENT 'wallet json info',
	PRIMARY KEY (user_id)
) COMMENT = 'Wallet Backup' DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;