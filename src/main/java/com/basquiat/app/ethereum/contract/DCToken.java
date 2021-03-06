package com.basquiat.app.ethereum.contract;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.3.1.
 */
@SuppressWarnings({"rawtypes", "unused"})
public class DCToken extends Contract {
    private static final String BINARY = "606060405260408051908101604052600781527f4443546f6b656e00000000000000000000000000000000000000000000000000602082015260029080516200004d9291602001906200033e565b5060408051908101604052600281527f444300000000000000000000000000000000000000000000000000000000000060208201526003908051620000979291602001906200033e565b5060086004819055600060078190558154600160a060020a0319908116735b1fcba12b4757549bf5d0550af253de5e0f73c51790925560098054831673a703356488f7335269e5860ca41f787dff50b76c179055600a80548316731db0304a3f2a861e7ab8b7305af1dc1ee4c3e94d179055600b805460a060020a60ff02199316736e41aa4d64f9f4a190a9af2292f815259baef65c179290921690915567016345785d8a0000600c55683635c9adc5dea00000600d55635abbbc00600e55635b101c00600f55610dac6010556011819055601281905560135534156200017d57600080fd5b60008054600160a060020a031990811633600160a060020a0390811691821790921617825567016345785d8a000060075530168082526005602052604080832066470de4df82000090819055919291600080516020620012ca833981519152915190815260200160405180910390a3600b8054600160a060020a03908116600090815260056020526040808220662386f26fc10000908190559354909216929091600080516020620012ca83398151915291905190815260200160405180910390a360098054600160a060020a03908116600090815260056020526040808220666a94d74f430000908190559354909216929091600080516020620012ca83398151915291905190815260200160405180910390a3600a8054600160a060020a0390811660009081526005602052604080822066470de4df820000908190559354909216929091600080516020620012ca83398151915291905190815260200160405180910390a360088054600160a060020a0390811660009081526005602052604080822066470de4df820000908190559354909216929091600080516020620012ca83398151915291905190815260200160405180910390a3620003e3565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200038157805160ff1916838001178555620003b1565b82800160010185558215620003b1579182015b82811115620003b157825182559160200191906001019062000394565b50620003bf929150620003c3565b5090565b620003e091905b80821115620003bf5760008155600101620003ca565b90565b610ed780620003f36000396000f30060606040526004361061017f5763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166306fdde038114610189578063095ea7b31461021357806318160ddd146102495780631ed241951461026e57806323b872dd146102815780632e1a7d4d146102a9578063313ce567146102bf5780633be3a3f5146102d2578063456c8cac146102e857806350baa622146102fb57806354fc85ac146103115780635f504a8214610324578063619cf5f914610353578063627749e6146103e257806370a08231146103f557806374eb936b1461041457806379ba5097146104275780638da5cb5b1461043a57806395d89b411461044d578063a035b1fe14610460578063a6f2ae3a1461017f578063a9059cbb14610473578063b425688814610495578063dd62ed3e146104a8578063de1eb2d0146104cd578063e336e01d146104e0578063e36b0b37146104f3578063f1fb3ace14610506578063f2fde38b14610519578063f60ba33814610538575b61018761054e565b005b341561019457600080fd5b61019c610668565b60405160208082528190810183818151815260200191508051906020019080838360005b838110156101d85780820151838201526020016101c0565b50505050905090810190601f1680156102055780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561021e57600080fd5b610235600160a060020a0360043516602435610706565b604051901515815260200160405180910390f35b341561025457600080fd5b61025c6107c0565b60405190815260200160405180910390f35b341561027957600080fd5b61025c6107c6565b341561028c57600080fd5b610235600160a060020a0360043581169060243516604435610835565b34156102b457600080fd5b610187600435610982565b34156102ca57600080fd5b61025c6109ff565b34156102dd57600080fd5b610187600435610a05565b34156102f357600080fd5b610235610a22565b341561030657600080fd5b610187600435610a32565b341561031c57600080fd5b61025c610a8c565b341561032f57600080fd5b610337610a92565b604051600160a060020a03909116815260200160405180910390f35b341561035e57600080fd5b610235600460248135818101908301358060208181020160405190810160405280939291908181526020018383602002808284378201915050505050509190803590602001908201803590602001908080602002602001604051908101604052809392919081815260200183836020028082843750949650610aa195505050505050565b34156103ed57600080fd5b61025c610aff565b341561040057600080fd5b61025c600160a060020a0360043516610b05565b341561041f57600080fd5b61025c610b17565b341561043257600080fd5b610187610b1d565b341561044557600080fd5b610337610b69565b341561045857600080fd5b61019c610b78565b341561046b57600080fd5b61025c610be3565b341561047e57600080fd5b610235600160a060020a0360043516602435610be9565b34156104a057600080fd5b61025c610cce565b34156104b357600080fd5b61025c600160a060020a0360043581169060243516610cd4565b34156104d857600080fd5b61025c610cf1565b34156104eb57600080fd5b61025c610cf7565b34156104fe57600080fd5b610235610cfd565b341561051157600080fd5b61025c610d80565b341561052457600080fd5b610187600160a060020a0360043516610d86565b341561054357600080fd5b610187600435610dcd565b6000806000600c5434101580156105675750600d543411155b151561056f57fe5b600e5442101580156105825750600f5442105b151561058a57fe5b60115466470de4df820000101561059d57fe5b600b5460a060020a900460ff16156105b157fe5b6010543493506402540be4009084020491506105dd606467016345785d8a000004601402601154610dea565b9050808211156105eb578091505b6105f760115483610dfc565b6011556012546106079084610dfc565b6012556106143383610e20565b151561061f57600080fd5b33600160a060020a03167f1cbc5ab135991bd2b6a4b034a04aa2aa086dac1371cb9b16b8b5e2ed6b036bed848460405191825260208201526040908101905180910390a2505050565b60028054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156106fe5780601f106106d3576101008083540402835291602001916106fe565b820191906000526020600020905b8154815290600101906020018083116106e157829003601f168201915b505050505081565b600082600160a060020a038116151561071b57fe5b82158061074b5750600160a060020a03338116600090815260066020908152604080832093881683529290522054155b151561075657600080fd5b600160a060020a03338116600081815260066020908152604080832094891680845294909152908190208690557f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b9259086905190815260200160405180910390a35060019392505050565b60075481565b600b5460009060a060020a900460ff16156107e357506002610832565b600e544210156107f557506000610832565b60115466470de4df820000141561080e57506002610832565b600e5442101580156108215750600f5442105b1561082e57506001610832565b5060025b90565b600083600160a060020a038116151561084a57fe5b83600160a060020a038116151561085d57fe5b600160a060020a0386166000908152600560205260409020548490101561088357600080fd5b600160a060020a03851660009081526005602052604090205484810110156108aa57600080fd5b600160a060020a0380871660009081526006602090815260408083203390941683529290522054849010156108de57600080fd5b6108e784610e91565b15156108f257600080fd5b600160a060020a03808616600081815260056020908152604080832080548a0190558a851680845281842080548b90039055600683528184203390961684529490915290819020805488900390559091907fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef9087905190815260200160405180910390a350600195945050505050565b6000805433600160a060020a0390811691161461099b57fe5b6109a36107c6565b9050600281146109b257600080fd5b600160a060020a03301631829010156109ca57600080fd5b600160a060020a03331682156108fc0283604051600060405180830381858888f1935050505015156109fb57600080fd5b5050565b60045481565b60005433600160a060020a03908116911614610a1d57fe5b600f55565b600b5460a060020a900460ff1681565b6000805433600160a060020a03908116911614610a4b57fe5b610a536107c6565b905060028114610a6257600080fd5b610a6e60135483610dfc565b601380549091019055610a813383610e20565b15156109fb57600080fd5b600d5481565b600154600160a060020a031681565b6000805b8351811015610af557610ae2848281518110610abd57fe5b90602001906020020151848381518110610ad357fe5b90602001906020020151610be9565b1515610aed57600080fd5b600101610aa5565b5060019392505050565b600f5481565b60056020526000908152604090205481565b60125481565b60015433600160a060020a03908116911614610b3557fe5b600180546000805473ffffffffffffffffffffffffffffffffffffffff19908116600160a060020a03841617909155169055565b600054600160a060020a031681565b60038054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156106fe5780601f106106d3576101008083540402835291602001916106fe565b60105481565b600082600160a060020a0381161515610bfe57fe5b600160a060020a03331660009081526005602052604090205483901015610c2457600080fd5b600160a060020a0384166000908152600560205260409020548381011015610c4b57600080fd5b610c5483610e91565b1515610c5f57600080fd5b600160a060020a033381166000818152600560205260408082208054889003905592871680825290839020805487019055917fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef9086905190815260200160405180910390a35060019392505050565b600e5481565b600660209081526000928352604080842090915290825290205481565b60135481565b60115481565b6000805433600160a060020a03908116911614610d1657fe5b600b5460a060020a900460ff1615610d2a57fe5b600b805474ff0000000000000000000000000000000000000000191660a060020a1790557f7944875bc330dfe56647ffb581e05b83c164c16fdef772625f6dfa284872921060405160405180910390a150600190565b600c5481565b60005433600160a060020a03908116911614610d9e57fe5b6001805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0392909216919091179055565b60005433600160a060020a03908116911614610de557fe5b600e55565b600082821115610df657fe5b50900390565b6000828201838110801590610e115750828110155b1515610e1957fe5b9392505050565b600160a060020a033081166000818152600560205260408082208054869003905592851680825283822080548601905590929091907fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef9085905190815260200160405180910390a350600192915050565b6000811515610ea257506000610ea6565b5060015b9190505600a165627a7a7230582023a63d8fa7a3159f164f32816cb5085161deea0a6408fa59e35106ab6821c5220029ddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef";

    protected DCToken(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected DCToken(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Transfer", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(event, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse._value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<TransferEventResponse> transferEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("Transfer", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(event, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.log = log;
                typedResponse._from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse._to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse._value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Approval", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(event, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._spender = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse._value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ApprovalEventResponse> approvalEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("Approval", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, ApprovalEventResponse>() {
            @Override
            public ApprovalEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(event, log);
                ApprovalEventResponse typedResponse = new ApprovalEventResponse();
                typedResponse.log = log;
                typedResponse._owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse._spender = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse._value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public List<BuyEventResponse> getBuyEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Buy", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(event, transactionReceipt);
        ArrayList<BuyEventResponse> responses = new ArrayList<BuyEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            BuyEventResponse typedResponse = new BuyEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.sender = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.eth = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.token = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<BuyEventResponse> buyEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("Buy", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, BuyEventResponse>() {
            @Override
            public BuyEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(event, log);
                BuyEventResponse typedResponse = new BuyEventResponse();
                typedResponse.log = log;
                typedResponse.sender = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.eth = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.token = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public List<StopSaleEventResponse> getStopSaleEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("StopSale", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList());
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(event, transactionReceipt);
        ArrayList<StopSaleEventResponse> responses = new ArrayList<StopSaleEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            StopSaleEventResponse typedResponse = new StopSaleEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<StopSaleEventResponse> stopSaleEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("StopSale", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList());
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, StopSaleEventResponse>() {
            @Override
            public StopSaleEventResponse call(Log log) {
				Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(event, log);
                StopSaleEventResponse typedResponse = new StopSaleEventResponse();
                typedResponse.log = log;
                return typedResponse;
            }
        });
    }

    public RemoteCall<String> name() {
        final Function function = new Function("name", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> approve(String _spender, BigInteger _value) {
        final Function function = new Function(
                "approve", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_spender), 
                new org.web3j.abi.datatypes.generated.Uint256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> totalSupply() {
		final Function function = new Function("totalSupply", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getPeriod() {
        final Function function = new Function("getPeriod", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> transferFrom(String _from, String _to, BigInteger _value) {
        final Function function = new Function(
                "transferFrom", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_from), 
                new org.web3j.abi.datatypes.Address(_to), 
                new org.web3j.abi.datatypes.generated.Uint256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> withdraw(BigInteger amount) {
        final Function function = new Function(
                "withdraw", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> decimals() {
        final Function function = new Function("decimals", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setCloseTime(BigInteger newCloseTime) {
        final Function function = new Function(
                "setCloseTime", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(newCloseTime)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Boolean> saleStopped() {
        final Function function = new Function("saleStopped", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<TransactionReceipt> withdrawToken(BigInteger amount) {
        final Function function = new Function(
                "withdrawToken", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> maxEth() {
        final Function function = new Function("maxEth", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> ownerCandidate() {
        final Function function = new Function("ownerCandidate", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> batchtransfer(List<String> _to, List<BigInteger> _amount) {
        final Function function = new Function(
                "batchtransfer", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.Utils.typeMap(_to, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.Utils.typeMap(_amount, org.web3j.abi.datatypes.generated.Uint256.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> closeTime() {
        final Function function = new Function("closeTime", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> balanceOf(String param0) {
        final Function function = new Function("balanceOf", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> ethQuantity() {
        final Function function = new Function("ethQuantity", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> acceptOwnership() {
        final Function function = new Function(
                "acceptOwnership", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> owner() {
        final Function function = new Function("owner", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> symbol() {
        final Function function = new Function("symbol", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> price() {
        final Function function = new Function("price", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> buy(BigInteger weiValue) {
        final Function function = new Function(
                "buy", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<TransactionReceipt> transfer(String _to, BigInteger _value) {
        final Function function = new Function(
                "transfer", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_to), 
                new org.web3j.abi.datatypes.generated.Uint256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> openTime() {
        final Function function = new Function("openTime", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> allowance(String param0, String param1) {
        final Function function = new Function("allowance", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0), 
                new org.web3j.abi.datatypes.Address(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> withdrawQuantity() {
        final Function function = new Function("withdrawQuantity", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> saleQuantity() {
        final Function function = new Function("saleQuantity", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> stopSale() {
        final Function function = new Function(
                "stopSale", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> minEth() {
        final Function function = new Function("minEth", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> transferOwnership(String candidate) {
        final Function function = new Function(
                "transferOwnership", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(candidate)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setOpenTime(BigInteger newOpenTime) {
        final Function function = new Function(
                "setOpenTime", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(newOpenTime)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<DCToken> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DCToken.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<DCToken> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DCToken.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static DCToken load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new DCToken(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static DCToken load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new DCToken(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class TransferEventResponse {
        public Log log;

        public String _from;

        public String _to;

        public BigInteger _value;
    }

    public static class ApprovalEventResponse {
        public Log log;

        public String _owner;

        public String _spender;

        public BigInteger _value;
    }

    public static class BuyEventResponse {
        public Log log;

        public String sender;

        public BigInteger eth;

        public BigInteger token;
    }

    public static class StopSaleEventResponse {
        public Log log;
    }
}
