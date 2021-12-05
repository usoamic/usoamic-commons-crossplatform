package io.usoamic.commons.crossplatform.mappers.entity

import io.usoamic.commons.crossplatform.exceptions.ContractNullThrowable
import io.usoamic.usoamickt.util.Coin
import org.web3j.utils.Convert
import java.math.BigDecimal
import java.math.BigInteger

fun BigInteger?.toCoin(): BigDecimal {
    return this?.let {
        Coin.fromSat(it).toBigDecimal()
    } ?: throw ContractNullThrowable("toCoin()")
}

fun BigInteger?.toEther(): BigDecimal {
    return this?.let {
        Convert.fromWei(it.toString(), Convert.Unit.ETHER)
    } ?: throw ContractNullThrowable("toEther()")
}