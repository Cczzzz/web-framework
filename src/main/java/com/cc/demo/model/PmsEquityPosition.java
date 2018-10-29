package com.cc.demo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "PMS_EQUITY_POSITION")
public class PmsEquityPosition implements Serializable {
    @Id
    @Column(name = "PORTFOLIO_CODE")
    private String portfolioCode;

    @Id
    @Column(name = "HIS_DATE")
    private Date hisDate;

    @Id
    @Column(name = "SECU_CODE")
    private String secuCode;

    @Id
    @Column(name = "ASSET_STATUS")
    private String assetStatus;

    @Column(name = "PARENT_PORTFOLIO_CODE")
    private String parentPortfolioCode;

    @Column(name = "ORG_DATE")
    private Date orgDate;

    @Column(name = "SECU_NAME")
    private String secuName;

    @Column(name = "SECU_TYPE_CODE")
    private String secuTypeCode;

    @Column(name = "PROPRE_STATUS")
    private String propreStatus;

    @Column(name = "SYMBOL")
    private String symbol;

    @Column(name = "MARKET_CODE")
    private String marketCode;

    @Column(name = "FIRST_BUY_DATE")
    private Date firstBuyDate;

    @Column(name = "CCY")
    private String ccy;

    @Column(name = "PE_QTY")
    private Long peQty;

    @Column(name = "MARKET_PRICE")
    private BigDecimal marketPrice;

    @Column(name = "VALUATION_PRICE")
    private BigDecimal valuationPrice;

    @Column(name = "PREV_MARKET_PRICE")
    private BigDecimal prevMarketPrice;

    @Column(name = "PREV_VALUATION_PRICE")
    private BigDecimal prevValuationPrice;

    @Column(name = "UNIT_COST")
    private BigDecimal unitCost;

    @Column(name = "POS_COST")
    private BigDecimal posCost;

    @Column(name = "MKT_VALUE")
    private BigDecimal mktValue;

    @Column(name = "PREV_MKT_VALUE")
    private BigDecimal prevMktValue;

    @Column(name = "WEIGHT_FOR_ALLOCATION_ASSET")
    private BigDecimal weightForAllocationAsset;

    @Column(name = "WEIGHT_FOR_NET_ASSET")
    private BigDecimal weightForNetAsset;

    @Column(name = "WEIGHT_FOR_TOTAL_ASSET")
    private BigDecimal weightForTotalAsset;

    @Column(name = "DAILY_UNREALIZED_PNL")
    private BigDecimal dailyUnrealizedPnl;

    @Column(name = "DAILY_REALIZED_PNL")
    private BigDecimal dailyRealizedPnl;

    @Column(name = "DAILY_PNL")
    private BigDecimal dailyPnl;

    @Column(name = "ACCUM_PNL")
    private BigDecimal accumPnl;

    @Column(name = "DAILY_CONTRIBUTION")
    private BigDecimal dailyContribution;

    @Column(name = "DAILY_RETURN")
    private BigDecimal dailyReturn;

    @Column(name = "PREV_YTD_RETURN")
    private BigDecimal prevYtdReturn;

    @Column(name = "YTD_RETURN")
    private BigDecimal ytdReturn;

    @Column(name = "PREV_CUMUL_RETURN")
    private BigDecimal prevCumulReturn;

    @Column(name = "CUMUL_RETURN")
    private BigDecimal cumulReturn;

    @Column(name = "PE")
    private BigDecimal pe;

    @Column(name = "PB")
    private BigDecimal pb;

    @Column(name = "VOLATILITY")
    private BigDecimal volatility;

    @Column(name = "TURNOVER")
    private BigDecimal turnover;

    @Column(name = "HEDGE_RATIO")
    private BigDecimal hedgeRatio;

    @Column(name = "ACCUM_BUY_AMOUNT")
    private BigDecimal accumBuyAmount;

    @Column(name = "ACCUM_SELL_AMOUNT")
    private BigDecimal accumSellAmount;

    @Column(name = "TODAY_BUY_AMOUNT")
    private BigDecimal todayBuyAmount;

    @Column(name = "TODAY_SELL_AMOUNT")
    private BigDecimal todaySellAmount;

    private static final long serialVersionUID = 1L;

    /**
     * @return PORTFOLIO_CODE
     */
    public String getPortfolioCode() {
        return portfolioCode;
    }

    /**
     * @param portfolioCode
     */
    public void setPortfolioCode(String portfolioCode) {
        this.portfolioCode = portfolioCode == null ? null : portfolioCode.trim();
    }

    /**
     * @return HIS_DATE
     */
    public Date getHisDate() {
        return hisDate;
    }

    /**
     * @param hisDate
     */
    public void setHisDate(Date hisDate) {
        this.hisDate = hisDate;
    }

    /**
     * @return SECU_CODE
     */
    public String getSecuCode() {
        return secuCode;
    }

    /**
     * @param secuCode
     */
    public void setSecuCode(String secuCode) {
        this.secuCode = secuCode == null ? null : secuCode.trim();
    }

    /**
     * @return ASSET_STATUS
     */
    public String getAssetStatus() {
        return assetStatus;
    }

    /**
     * @param assetStatus
     */
    public void setAssetStatus(String assetStatus) {
        this.assetStatus = assetStatus == null ? null : assetStatus.trim();
    }

    /**
     * @return PARENT_PORTFOLIO_CODE
     */
    public String getParentPortfolioCode() {
        return parentPortfolioCode;
    }

    /**
     * @param parentPortfolioCode
     */
    public void setParentPortfolioCode(String parentPortfolioCode) {
        this.parentPortfolioCode = parentPortfolioCode == null ? null : parentPortfolioCode.trim();
    }

    /**
     * @return ORG_DATE
     */
    public Date getOrgDate() {
        return orgDate;
    }

    /**
     * @param orgDate
     */
    public void setOrgDate(Date orgDate) {
        this.orgDate = orgDate;
    }

    /**
     * @return SECU_NAME
     */
    public String getSecuName() {
        return secuName;
    }

    /**
     * @param secuName
     */
    public void setSecuName(String secuName) {
        this.secuName = secuName == null ? null : secuName.trim();
    }

    /**
     * @return SECU_TYPE_CODE
     */
    public String getSecuTypeCode() {
        return secuTypeCode;
    }

    /**
     * @param secuTypeCode
     */
    public void setSecuTypeCode(String secuTypeCode) {
        this.secuTypeCode = secuTypeCode == null ? null : secuTypeCode.trim();
    }

    /**
     * @return PROPRE_STATUS
     */
    public String getPropreStatus() {
        return propreStatus;
    }

    /**
     * @param propreStatus
     */
    public void setPropreStatus(String propreStatus) {
        this.propreStatus = propreStatus == null ? null : propreStatus.trim();
    }

    /**
     * @return SYMBOL
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * @param symbol
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol == null ? null : symbol.trim();
    }

    /**
     * @return MARKET_CODE
     */
    public String getMarketCode() {
        return marketCode;
    }

    /**
     * @param marketCode
     */
    public void setMarketCode(String marketCode) {
        this.marketCode = marketCode == null ? null : marketCode.trim();
    }

    /**
     * @return FIRST_BUY_DATE
     */
    public Date getFirstBuyDate() {
        return firstBuyDate;
    }

    /**
     * @param firstBuyDate
     */
    public void setFirstBuyDate(Date firstBuyDate) {
        this.firstBuyDate = firstBuyDate;
    }

    /**
     * @return CCY
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * @param ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * @return PE_QTY
     */
    public Long getPeQty() {
        return peQty;
    }

    /**
     * @param peQty
     */
    public void setPeQty(Long peQty) {
        this.peQty = peQty;
    }

    /**
     * @return MARKET_PRICE
     */
    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    /**
     * @param marketPrice
     */
    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    /**
     * @return VALUATION_PRICE
     */
    public BigDecimal getValuationPrice() {
        return valuationPrice;
    }

    /**
     * @param valuationPrice
     */
    public void setValuationPrice(BigDecimal valuationPrice) {
        this.valuationPrice = valuationPrice;
    }

    /**
     * @return PREV_MARKET_PRICE
     */
    public BigDecimal getPrevMarketPrice() {
        return prevMarketPrice;
    }

    /**
     * @param prevMarketPrice
     */
    public void setPrevMarketPrice(BigDecimal prevMarketPrice) {
        this.prevMarketPrice = prevMarketPrice;
    }

    /**
     * @return PREV_VALUATION_PRICE
     */
    public BigDecimal getPrevValuationPrice() {
        return prevValuationPrice;
    }

    /**
     * @param prevValuationPrice
     */
    public void setPrevValuationPrice(BigDecimal prevValuationPrice) {
        this.prevValuationPrice = prevValuationPrice;
    }

    /**
     * @return UNIT_COST
     */
    public BigDecimal getUnitCost() {
        return unitCost;
    }

    /**
     * @param unitCost
     */
    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    /**
     * @return POS_COST
     */
    public BigDecimal getPosCost() {
        return posCost;
    }

    /**
     * @param posCost
     */
    public void setPosCost(BigDecimal posCost) {
        this.posCost = posCost;
    }

    /**
     * @return MKT_VALUE
     */
    public BigDecimal getMktValue() {
        return mktValue;
    }

    /**
     * @param mktValue
     */
    public void setMktValue(BigDecimal mktValue) {
        this.mktValue = mktValue;
    }

    /**
     * @return PREV_MKT_VALUE
     */
    public BigDecimal getPrevMktValue() {
        return prevMktValue;
    }

    /**
     * @param prevMktValue
     */
    public void setPrevMktValue(BigDecimal prevMktValue) {
        this.prevMktValue = prevMktValue;
    }

    /**
     * @return WEIGHT_FOR_ALLOCATION_ASSET
     */
    public BigDecimal getWeightForAllocationAsset() {
        return weightForAllocationAsset;
    }

    /**
     * @param weightForAllocationAsset
     */
    public void setWeightForAllocationAsset(BigDecimal weightForAllocationAsset) {
        this.weightForAllocationAsset = weightForAllocationAsset;
    }

    /**
     * @return WEIGHT_FOR_NET_ASSET
     */
    public BigDecimal getWeightForNetAsset() {
        return weightForNetAsset;
    }

    /**
     * @param weightForNetAsset
     */
    public void setWeightForNetAsset(BigDecimal weightForNetAsset) {
        this.weightForNetAsset = weightForNetAsset;
    }

    /**
     * @return WEIGHT_FOR_TOTAL_ASSET
     */
    public BigDecimal getWeightForTotalAsset() {
        return weightForTotalAsset;
    }

    /**
     * @param weightForTotalAsset
     */
    public void setWeightForTotalAsset(BigDecimal weightForTotalAsset) {
        this.weightForTotalAsset = weightForTotalAsset;
    }

    /**
     * @return DAILY_UNREALIZED_PNL
     */
    public BigDecimal getDailyUnrealizedPnl() {
        return dailyUnrealizedPnl;
    }

    /**
     * @param dailyUnrealizedPnl
     */
    public void setDailyUnrealizedPnl(BigDecimal dailyUnrealizedPnl) {
        this.dailyUnrealizedPnl = dailyUnrealizedPnl;
    }

    /**
     * @return DAILY_REALIZED_PNL
     */
    public BigDecimal getDailyRealizedPnl() {
        return dailyRealizedPnl;
    }

    /**
     * @param dailyRealizedPnl
     */
    public void setDailyRealizedPnl(BigDecimal dailyRealizedPnl) {
        this.dailyRealizedPnl = dailyRealizedPnl;
    }

    /**
     * @return DAILY_PNL
     */
    public BigDecimal getDailyPnl() {
        return dailyPnl;
    }

    /**
     * @param dailyPnl
     */
    public void setDailyPnl(BigDecimal dailyPnl) {
        this.dailyPnl = dailyPnl;
    }

    /**
     * @return ACCUM_PNL
     */
    public BigDecimal getAccumPnl() {
        return accumPnl;
    }

    /**
     * @param accumPnl
     */
    public void setAccumPnl(BigDecimal accumPnl) {
        this.accumPnl = accumPnl;
    }

    /**
     * @return DAILY_CONTRIBUTION
     */
    public BigDecimal getDailyContribution() {
        return dailyContribution;
    }

    /**
     * @param dailyContribution
     */
    public void setDailyContribution(BigDecimal dailyContribution) {
        this.dailyContribution = dailyContribution;
    }

    /**
     * @return DAILY_RETURN
     */
    public BigDecimal getDailyReturn() {
        return dailyReturn;
    }

    /**
     * @param dailyReturn
     */
    public void setDailyReturn(BigDecimal dailyReturn) {
        this.dailyReturn = dailyReturn;
    }

    /**
     * @return PREV_YTD_RETURN
     */
    public BigDecimal getPrevYtdReturn() {
        return prevYtdReturn;
    }

    /**
     * @param prevYtdReturn
     */
    public void setPrevYtdReturn(BigDecimal prevYtdReturn) {
        this.prevYtdReturn = prevYtdReturn;
    }

    /**
     * @return YTD_RETURN
     */
    public BigDecimal getYtdReturn() {
        return ytdReturn;
    }

    /**
     * @param ytdReturn
     */
    public void setYtdReturn(BigDecimal ytdReturn) {
        this.ytdReturn = ytdReturn;
    }

    /**
     * @return PREV_CUMUL_RETURN
     */
    public BigDecimal getPrevCumulReturn() {
        return prevCumulReturn;
    }

    /**
     * @param prevCumulReturn
     */
    public void setPrevCumulReturn(BigDecimal prevCumulReturn) {
        this.prevCumulReturn = prevCumulReturn;
    }

    /**
     * @return CUMUL_RETURN
     */
    public BigDecimal getCumulReturn() {
        return cumulReturn;
    }

    /**
     * @param cumulReturn
     */
    public void setCumulReturn(BigDecimal cumulReturn) {
        this.cumulReturn = cumulReturn;
    }

    /**
     * @return PE
     */
    public BigDecimal getPe() {
        return pe;
    }

    /**
     * @param pe
     */
    public void setPe(BigDecimal pe) {
        this.pe = pe;
    }

    /**
     * @return PB
     */
    public BigDecimal getPb() {
        return pb;
    }

    /**
     * @param pb
     */
    public void setPb(BigDecimal pb) {
        this.pb = pb;
    }

    /**
     * @return VOLATILITY
     */
    public BigDecimal getVolatility() {
        return volatility;
    }

    /**
     * @param volatility
     */
    public void setVolatility(BigDecimal volatility) {
        this.volatility = volatility;
    }

    /**
     * @return TURNOVER
     */
    public BigDecimal getTurnover() {
        return turnover;
    }

    /**
     * @param turnover
     */
    public void setTurnover(BigDecimal turnover) {
        this.turnover = turnover;
    }

    /**
     * @return HEDGE_RATIO
     */
    public BigDecimal getHedgeRatio() {
        return hedgeRatio;
    }

    /**
     * @param hedgeRatio
     */
    public void setHedgeRatio(BigDecimal hedgeRatio) {
        this.hedgeRatio = hedgeRatio;
    }

    /**
     * @return ACCUM_BUY_AMOUNT
     */
    public BigDecimal getAccumBuyAmount() {
        return accumBuyAmount;
    }

    /**
     * @param accumBuyAmount
     */
    public void setAccumBuyAmount(BigDecimal accumBuyAmount) {
        this.accumBuyAmount = accumBuyAmount;
    }

    /**
     * @return ACCUM_SELL_AMOUNT
     */
    public BigDecimal getAccumSellAmount() {
        return accumSellAmount;
    }

    /**
     * @param accumSellAmount
     */
    public void setAccumSellAmount(BigDecimal accumSellAmount) {
        this.accumSellAmount = accumSellAmount;
    }

    /**
     * @return TODAY_BUY_AMOUNT
     */
    public BigDecimal getTodayBuyAmount() {
        return todayBuyAmount;
    }

    /**
     * @param todayBuyAmount
     */
    public void setTodayBuyAmount(BigDecimal todayBuyAmount) {
        this.todayBuyAmount = todayBuyAmount;
    }

    /**
     * @return TODAY_SELL_AMOUNT
     */
    public BigDecimal getTodaySellAmount() {
        return todaySellAmount;
    }

    /**
     * @param todaySellAmount
     */
    public void setTodaySellAmount(BigDecimal todaySellAmount) {
        this.todaySellAmount = todaySellAmount;
    }
}