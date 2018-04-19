# Start

## 1. couchbase 설치
	
MySQL과는 별개로  couchbase는 발생하는 모든 트랜잭션의 JSON정보를 그대로 저장하기 위한 폴리그랏 저장소로 운영된다.
Transaction Log의 형식을 취한다.
		
See https://www.couchbase.com/downloads

Server의 Community버전을 OS에 맞게 설치한다.

### 1.1 bucket설정
bucket은 일종의 database개념으로 생각하면 된다. 
이 bucket명은 다음과 같이 application.yml에 설정으로 들어간다.
couchbase와 설치 및 관련 정보는 다음 블로그에 잘 정리되어 있다.

http://bcho.tistory.com/924

java 프로그램 레벨에서 N1QL를 사용하기 위해서는 index를 생성해야한다.

couchbase console에서 Query 카테고리에서 다음과 같은 명령어를 날려 인덱스를 생성한다.

CREATE PRIMARY INDEX `<<index name>>` ON `<<bucket name>>`

e.g.

> CREATE PRIMARY INDEX `basquiat-primary-index` ON `basquiat`
    
## 2. MySql 설치

	
## 3. Web3J tool 설치

###	3.1 solc Compiler
npm을 통해서 받는다.
	 
>npm install -g solc
	  
해당프로젝트에 관련 툴은   폴더에 있다.
	  
	  
### 3.2 Web3j Command Tool
See  https://github.com/web3j/web3j/releases
OS에 맞게 다운로드 하고 압축을 푼다.
현재 프로젝트에는   폴더에 포함시켜놨다.	
	  
## 4. Smart Contract를 사용하기 위한 설정
token을 생성한 sol파일이 있다면 다음과 같은 방식으로 진행하면 된다.
현재 프로젝트는 DCToken를 기준으로 작성되었기때문에 다른 토큰을 스마트 컨트랙트로 사용하기 위해서는 코드 수정이 필요하다.
 
### 4.1 solidity convert to Java
이더리움 플랫폼에 token을 생성할때의 sol파일을 프로젝트의 sol 폴더에 저장한다.

#### 4.1.1. abi와 bin파일 분리하기.
cmd창에서 해당 sol폴더로 들어간다.
cmd창에서 다음과 같이 명령어를 날린다.
   	  
> solc DCToken.sol --bin --abi --optimize -o C:\Users\basquiat\Documents\sts\ethereum\eva-web3j\sol\

이 명령어는 다음과 같은 의미를 지닌다.

> solc <<token 생성한 sol파일의 이름>> --bin --abi --optimize -o <<분리된 파일이 저장될 폴더 위치>>
   	  
이 명령어를 날리면 해당 sol파일의 컨트랙트 부분의 abi와 bin파일들이 생성된다.
   	  
#### 4.1.2 token contract를 자바 파일로 변환하기
생성된 많은 파일들 중 Token과 관련된 파일을 자바로 변환한다.
cmd에서 web3j폴더의 bin 폴더로 이동후 web3j.bat를 실행한다. 
   	  	

		              _      _____ _     _
		             | |    |____ (_)   (_)
		__      _____| |__      / /_     _   ___
		\ \ /\ / / _ \ '_ \     \ \ |   | | / _ \
		 \ V  V /  __/ |_) |.___/ / | _ | || (_) |
		  \_/\_/ \___|_.__/ \____/| |(_)|_| \___/
		                         _/ |
		                        |__/
		
		Usage: web3j version|wallet|solidity ...
		
		C:\Users\basquiat\Documents\web3j-3.3.1\bin>

실행을 하면 다음과 같은 화면이 뜨면서 명령어를 칠 수 있게 된다.
우리의 관심사는 토큰에 대한 스마트 컨트랙트를 자바에서 사용하기 위함이다.

다음과 같이 명령어를 날린다.

> web3j solidity generate C:\Users\basquiat\Documents\sts\ethereum\eva-web3j\sol\DCToken.bin C:\Users\basquiat\Documents\sts\ethereum\eva-web3j\sol\DCToken.abi -o C:\Users\basquiat\Documents\sts\ethereum\eva-web3j\src\main\java -p com.basquiat.app.ethereum.contract

여기서 -o 뒤쪽 명령어중 첫 번째 패쓰는 폴더 위치이며 -p 뒤에 명령어를 변환된 자바 파일이 생성될 패키지를 지정한다.


## 5. 생성된 컨트랙트 자바를 프로젝트에 적용하기.

See  
	
Web3JConfig에서 생성한 컨트랙트 자바를 사용하면 된다.

현재 프로젝트에서는 DCToken를 사용하고 있다.

## 6. Application.yml 설정

See  
 
  위에서 couchbase를 설치할때의 bucket명과 password를 설정한다.
  ethereum rpc info 부분에
  최초 ethCoinbase의 패스워드와 wallet의 파일의 private Key설정을 한다.
  token을 이더리움 플랫폼에 생성시 발급받은 token address를 설정한다.  
  
## 7. Swagger UI 사용

http://localhost:8090/basquiat/swagger-ui.html#/

## 8. Ethereum Node 실행

> geth --rpc --rpcport 8545 --rpcaddr 127.0.0.1 --rpccorsdomain "*" --rpcapi "eth,web3,net" --datadir C:\Users\basquiat\AppData\Roaming\Ethereum\basquiat --networkid=63 --port=60606 console

해당 어플리케이션이 붙을 노드를 실행할 때는 CORS방지와 web3j와 Node간 통신을 위해서는 --rpcapi "eth, web3,net" 옵션을 주고 실행해야 한다.

## 9. Web3J 버전
만일 프라이빗 네트워크에서 생성시 현재 컨트랙트 관련 버그 이슈가 있다.
이것은    	  