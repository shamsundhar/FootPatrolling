
package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "ProductDto")
public class ProductDto {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "amountUomTypeId")
    private String amountUomTypeId;
    @ColumnInfo(name = "autoCreateKeywords")
    private String autoCreateKeywords;
    @ColumnInfo(name = "billOfMaterialLevel")
    private String billOfMaterialLevel;
    @ColumnInfo(name = "brandName")
    private String brandName;
    @ColumnInfo(name = "chargeShipping")
    private String chargeShipping;
    @ColumnInfo(name = "comments")
    private String comments;
    @ColumnInfo(name = "configId")
    private String configId;
    @ColumnInfo(name = "createdByUserLogin")
    private String createdByUserLogin;
    @ColumnInfo(name = "createdDate")
    private String createdDate;
    @ColumnInfo(name = "createdStamp")
    private String createdStamp;
    @ColumnInfo(name = "createdTxStamp")
    private String createdTxStamp;
    @ColumnInfo(name = "defaultShipmentBoxTypeId")
    private String defaultShipmentBoxTypeId;
    @ColumnInfo(name = "depthUomId")
    private String depthUomId;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "detailImageUrl")
    private String detailImageUrl;
    @ColumnInfo(name = "detailScreen")
    private String detailScreen;
    @ColumnInfo(name = "diameterUomId")
    private String diameterUomId;
    @ColumnInfo(name = "facilityId")
    private String facilityId;
    @ColumnInfo(name = "fixedAmount")
    private String fixedAmount;
    @ColumnInfo(name = "heightUomId")
    private String heightUomId;
    @ColumnInfo(name = "includeInPromotions")
    private String includeInPromotions;
    @ColumnInfo(name = "inShippingBox")
    private String inShippingBox;
    @ColumnInfo(name = "internalName")
    private String internalName;
    @ColumnInfo(name = "introductionDate")
    private String introductionDate;
    @ColumnInfo(name = "inventoryMessage")
    private String inventoryMessage;
    @ColumnInfo(name = "isActive")
    private String isActive;
    @ColumnInfo(name = "isSerialized")
    private String isSerialized;
    @ColumnInfo(name = "isVariant")
    private String isVariant;
    @ColumnInfo(name = "isVirtual")
    private String isVirtual;
    @ColumnInfo(name = "largeImageUrl")
    private String largeImageUrl;
    @ColumnInfo(name = "lastModifiedByUserLogin")
    private String lastModifiedByUserLogin;
    @ColumnInfo(name = "lastModifiedDate")
    private String lastModifiedDate;
    @ColumnInfo(name = "lastUpdatedStamp")
    private String lastUpdatedStamp;
    @ColumnInfo(name = "lastUpdatedTxStamp")
    private String lastUpdatedTxStamp;
    @ColumnInfo(name = "longDescription")
    private String longDescription;
    @ColumnInfo(name = "manufacturerPartyId")
    private String manufacturerPartyId;
    @ColumnInfo(name = "materialClassification")
    private String materialClassification;
    @ColumnInfo(name = "mediumImageUrl")
    private String mediumImageUrl;
    @ColumnInfo(name = "originalImageUrl")
    private String originalImageUrl;
    @ColumnInfo(name = "originGeoId")
    private String originGeoId;
    @ColumnInfo(name = "piecesIncluded")
    private String piecesIncluded;
    @ColumnInfo(name = "plNo")
    private String plNo;
    @ColumnInfo(name = "priceDetailText")
    private String priceDetailText;
    @ColumnInfo(name = "primaryProductCategoryId")
    private String primaryProductCategoryId;
    @ColumnInfo(name = "productCodeTypeId")
    private String productCodeTypeId;
    @ColumnInfo(name = "productDepth")
    private String productDepth;
    @ColumnInfo(name = "productDiameter")
    private String productDiameter;
    @ColumnInfo(name = "productHeight")
    private String productHeight;
    @ColumnInfo(name = "productId")
    private String productId;
    @ColumnInfo(name = "productMakeDetails")
    private String productMakeDetails;
    @ColumnInfo(name = "productName")
    private String productName;
    @ColumnInfo(name = "productRating")
    private String productRating;
    @ColumnInfo(name = "productTypeId")
    private String productTypeId;
    @ColumnInfo(name = "productWeight")
    private String productWeight;
    @ColumnInfo(name = "productWidth")
    private String productWidth;
    @ColumnInfo(name = "quantityIncluded")
    private String quantityIncluded;
    @ColumnInfo(name = "quantityUomId")
    private String quantityUomId;
    @ColumnInfo(name = "ratingTypeEnum")
    private String ratingTypeEnum;
    @ColumnInfo(name = "releaseDate")
    private String releaseDate;
    @ColumnInfo(name = "requireAmount")
    private String requireAmount;
    @ColumnInfo(name = "requireInventory")
    private String requireInventory;
    @ColumnInfo(name = "requirementMethodEnumId")
    private String requirementMethodEnumId;
    @ColumnInfo(name = "reserv2ndPPPerc")
    private String reserv2ndPPPerc;
    @ColumnInfo(name = "reservMaxPersons")
    private String reservMaxPersons;
    @ColumnInfo(name = "reservNthPPPerc")
    private String reservNthPPPerc;
    @ColumnInfo(name = "returnable")
    private String returnable;
    @ColumnInfo(name = "rlyId")
    private String rlyId;
    @ColumnInfo(name = "salesDiscontinuationDate")
    private String salesDiscontinuationDate;
    @ColumnInfo(name = "salesDiscWhenNotAvail")
    private String salesDiscWhenNotAvail;
    @ColumnInfo(name = "shippingDepth")
    private String shippingDepth;
    @ColumnInfo(name = "shippingHeight")
    private String shippingHeight;
    @ColumnInfo(name = "shippingWidth")
    private String shippingWidth;
    @ColumnInfo(name = "smallImageUrl")
    private String smallImageUrl;
    @ColumnInfo(name = "supportDiscontinuationDate")
    private String supportDiscontinuationDate;
    @ColumnInfo(name = "taxable")
    private String taxable;
    @ColumnInfo(name = "trdDivId")
    private String trdDivId;
    @ColumnInfo(name = "virtualVariantMethodEnum")
    private String virtualVariantMethodEnum;
    @ColumnInfo(name = "weight")
    private String weight;
    @ColumnInfo(name = "weightUomId")
    private String weightUomId;
    @ColumnInfo(name = "widthUomId")
    private String widthUomId;

    public ProductDto() {
    }

    public String getAmountUomTypeId() {
        return amountUomTypeId;
    }

    public void setAmountUomTypeId(String amountUomTypeId) {
        this.amountUomTypeId = amountUomTypeId;
    }

    public String getAutoCreateKeywords() {
        return autoCreateKeywords;
    }

    public void setAutoCreateKeywords(String autoCreateKeywords) {
        this.autoCreateKeywords = autoCreateKeywords;
    }

    public String getBillOfMaterialLevel() {
        return billOfMaterialLevel;
    }

    public void setBillOfMaterialLevel(String billOfMaterialLevel) {
        this.billOfMaterialLevel = billOfMaterialLevel;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getChargeShipping() {
        return chargeShipping;
    }

    public void setChargeShipping(String chargeShipping) {
        this.chargeShipping = chargeShipping;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public String getCreatedByUserLogin() {
        return createdByUserLogin;
    }

    public void setCreatedByUserLogin(String createdByUserLogin) {
        this.createdByUserLogin = createdByUserLogin;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedStamp() {
        return createdStamp;
    }

    public void setCreatedStamp(String createdStamp) {
        this.createdStamp = createdStamp;
    }

    public String getCreatedTxStamp() {
        return createdTxStamp;
    }

    public void setCreatedTxStamp(String createdTxStamp) {
        this.createdTxStamp = createdTxStamp;
    }

    public String getDefaultShipmentBoxTypeId() {
        return defaultShipmentBoxTypeId;
    }

    public void setDefaultShipmentBoxTypeId(String defaultShipmentBoxTypeId) {
        this.defaultShipmentBoxTypeId = defaultShipmentBoxTypeId;
    }

    public String getDepthUomId() {
        return depthUomId;
    }

    public void setDepthUomId(String depthUomId) {
        this.depthUomId = depthUomId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetailImageUrl() {
        return detailImageUrl;
    }

    public void setDetailImageUrl(String detailImageUrl) {
        this.detailImageUrl = detailImageUrl;
    }

    public String getDetailScreen() {
        return detailScreen;
    }

    public void setDetailScreen(String detailScreen) {
        this.detailScreen = detailScreen;
    }

    public String getDiameterUomId() {
        return diameterUomId;
    }

    public void setDiameterUomId(String diameterUomId) {
        this.diameterUomId = diameterUomId;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public String getFixedAmount() {
        return fixedAmount;
    }

    public void setFixedAmount(String fixedAmount) {
        this.fixedAmount = fixedAmount;
    }

    public String getHeightUomId() {
        return heightUomId;
    }

    public void setHeightUomId(String heightUomId) {
        this.heightUomId = heightUomId;
    }

    public String getIncludeInPromotions() {
        return includeInPromotions;
    }

    public void setIncludeInPromotions(String includeInPromotions) {
        this.includeInPromotions = includeInPromotions;
    }

    public String getInShippingBox() {
        return inShippingBox;
    }

    public void setInShippingBox(String inShippingBox) {
        this.inShippingBox = inShippingBox;
    }

    public String getInternalName() {
        return internalName;
    }

    public void setInternalName(String internalName) {
        this.internalName = internalName;
    }

    public String getIntroductionDate() {
        return introductionDate;
    }

    public void setIntroductionDate(String introductionDate) {
        this.introductionDate = introductionDate;
    }

    public String getInventoryMessage() {
        return inventoryMessage;
    }

    public void setInventoryMessage(String inventoryMessage) {
        this.inventoryMessage = inventoryMessage;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getIsSerialized() {
        return isSerialized;
    }

    public void setIsSerialized(String isSerialized) {
        this.isSerialized = isSerialized;
    }

    public String getIsVariant() {
        return isVariant;
    }

    public void setIsVariant(String isVariant) {
        this.isVariant = isVariant;
    }

    public String getIsVirtual() {
        return isVirtual;
    }

    public void setIsVirtual(String isVirtual) {
        this.isVirtual = isVirtual;
    }

    public String getLargeImageUrl() {
        return largeImageUrl;
    }

    public void setLargeImageUrl(String largeImageUrl) {
        this.largeImageUrl = largeImageUrl;
    }

    public String getLastModifiedByUserLogin() {
        return lastModifiedByUserLogin;
    }

    public void setLastModifiedByUserLogin(String lastModifiedByUserLogin) {
        this.lastModifiedByUserLogin = lastModifiedByUserLogin;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastUpdatedStamp() {
        return lastUpdatedStamp;
    }

    public void setLastUpdatedStamp(String lastUpdatedStamp) {
        this.lastUpdatedStamp = lastUpdatedStamp;
    }

    public String getLastUpdatedTxStamp() {
        return lastUpdatedTxStamp;
    }

    public void setLastUpdatedTxStamp(String lastUpdatedTxStamp) {
        this.lastUpdatedTxStamp = lastUpdatedTxStamp;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getManufacturerPartyId() {
        return manufacturerPartyId;
    }

    public void setManufacturerPartyId(String manufacturerPartyId) {
        this.manufacturerPartyId = manufacturerPartyId;
    }

    public String getMaterialClassification() {
        return materialClassification;
    }

    public void setMaterialClassification(String materialClassification) {
        this.materialClassification = materialClassification;
    }

    public String getMediumImageUrl() {
        return mediumImageUrl;
    }

    public void setMediumImageUrl(String mediumImageUrl) {
        this.mediumImageUrl = mediumImageUrl;
    }

    public String getOriginalImageUrl() {
        return originalImageUrl;
    }

    public void setOriginalImageUrl(String originalImageUrl) {
        this.originalImageUrl = originalImageUrl;
    }

    public String getOriginGeoId() {
        return originGeoId;
    }

    public void setOriginGeoId(String originGeoId) {
        this.originGeoId = originGeoId;
    }

    public String getPiecesIncluded() {
        return piecesIncluded;
    }

    public void setPiecesIncluded(String piecesIncluded) {
        this.piecesIncluded = piecesIncluded;
    }

    public String getPlNo() {
        return plNo;
    }

    public void setPlNo(String plNo) {
        this.plNo = plNo;
    }

    public String getPriceDetailText() {
        return priceDetailText;
    }

    public void setPriceDetailText(String priceDetailText) {
        this.priceDetailText = priceDetailText;
    }

    public String getPrimaryProductCategoryId() {
        return primaryProductCategoryId;
    }

    public void setPrimaryProductCategoryId(String primaryProductCategoryId) {
        this.primaryProductCategoryId = primaryProductCategoryId;
    }

    public String getProductCodeTypeId() {
        return productCodeTypeId;
    }

    public void setProductCodeTypeId(String productCodeTypeId) {
        this.productCodeTypeId = productCodeTypeId;
    }

    public String getProductDepth() {
        return productDepth;
    }

    public void setProductDepth(String productDepth) {
        this.productDepth = productDepth;
    }

    public String getProductDiameter() {
        return productDiameter;
    }

    public void setProductDiameter(String productDiameter) {
        this.productDiameter = productDiameter;
    }

    public String getProductHeight() {
        return productHeight;
    }

    public void setProductHeight(String productHeight) {
        this.productHeight = productHeight;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductMakeDetails() {
        return productMakeDetails;
    }

    public void setProductMakeDetails(String productMakeDetails) {
        this.productMakeDetails = productMakeDetails;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductRating() {
        return productRating;
    }

    public void setProductRating(String productRating) {
        this.productRating = productRating;
    }

    public String getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(String productWeight) {
        this.productWeight = productWeight;
    }

    public String getProductWidth() {
        return productWidth;
    }

    public void setProductWidth(String productWidth) {
        this.productWidth = productWidth;
    }

    public String getQuantityIncluded() {
        return quantityIncluded;
    }

    public void setQuantityIncluded(String quantityIncluded) {
        this.quantityIncluded = quantityIncluded;
    }

    public String getQuantityUomId() {
        return quantityUomId;
    }

    public void setQuantityUomId(String quantityUomId) {
        this.quantityUomId = quantityUomId;
    }

    public String getRatingTypeEnum() {
        return ratingTypeEnum;
    }

    public void setRatingTypeEnum(String ratingTypeEnum) {
        this.ratingTypeEnum = ratingTypeEnum;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRequireAmount() {
        return requireAmount;
    }

    public void setRequireAmount(String requireAmount) {
        this.requireAmount = requireAmount;
    }

    public String getRequireInventory() {
        return requireInventory;
    }

    public void setRequireInventory(String requireInventory) {
        this.requireInventory = requireInventory;
    }

    public String getRequirementMethodEnumId() {
        return requirementMethodEnumId;
    }

    public void setRequirementMethodEnumId(String requirementMethodEnumId) {
        this.requirementMethodEnumId = requirementMethodEnumId;
    }

    public String getReserv2ndPPPerc() {
        return reserv2ndPPPerc;
    }

    public void setReserv2ndPPPerc(String reserv2ndPPPerc) {
        this.reserv2ndPPPerc = reserv2ndPPPerc;
    }

    public String getReservMaxPersons() {
        return reservMaxPersons;
    }

    public void setReservMaxPersons(String reservMaxPersons) {
        this.reservMaxPersons = reservMaxPersons;
    }

    public String getReservNthPPPerc() {
        return reservNthPPPerc;
    }

    public void setReservNthPPPerc(String reservNthPPPerc) {
        this.reservNthPPPerc = reservNthPPPerc;
    }

    public String getReturnable() {
        return returnable;
    }

    public void setReturnable(String returnable) {
        this.returnable = returnable;
    }

    public String getRlyId() {
        return rlyId;
    }

    public void setRlyId(String rlyId) {
        this.rlyId = rlyId;
    }

    public String getSalesDiscontinuationDate() {
        return salesDiscontinuationDate;
    }

    public void setSalesDiscontinuationDate(String salesDiscontinuationDate) {
        this.salesDiscontinuationDate = salesDiscontinuationDate;
    }

    public String getSalesDiscWhenNotAvail() {
        return salesDiscWhenNotAvail;
    }

    public void setSalesDiscWhenNotAvail(String salesDiscWhenNotAvail) {
        this.salesDiscWhenNotAvail = salesDiscWhenNotAvail;
    }

    public String getShippingDepth() {
        return shippingDepth;
    }

    public void setShippingDepth(String shippingDepth) {
        this.shippingDepth = shippingDepth;
    }

    public String getShippingHeight() {
        return shippingHeight;
    }

    public void setShippingHeight(String shippingHeight) {
        this.shippingHeight = shippingHeight;
    }

    public String getShippingWidth() {
        return shippingWidth;
    }

    public void setShippingWidth(String shippingWidth) {
        this.shippingWidth = shippingWidth;
    }

    public String getSmallImageUrl() {
        return smallImageUrl;
    }

    public void setSmallImageUrl(String smallImageUrl) {
        this.smallImageUrl = smallImageUrl;
    }

    public String getSupportDiscontinuationDate() {
        return supportDiscontinuationDate;
    }

    public void setSupportDiscontinuationDate(String supportDiscontinuationDate) {
        this.supportDiscontinuationDate = supportDiscontinuationDate;
    }

    public String getTaxable() {
        return taxable;
    }

    public void setTaxable(String taxable) {
        this.taxable = taxable;
    }

    public String getTrdDivId() {
        return trdDivId;
    }

    public void setTrdDivId(String trdDivId) {
        this.trdDivId = trdDivId;
    }

    public String getVirtualVariantMethodEnum() {
        return virtualVariantMethodEnum;
    }

    public void setVirtualVariantMethodEnum(String virtualVariantMethodEnum) {
        this.virtualVariantMethodEnum = virtualVariantMethodEnum;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWeightUomId() {
        return weightUomId;
    }

    public void setWeightUomId(String weightUomId) {
        this.weightUomId = weightUomId;
    }

    public String getWidthUomId() {
        return widthUomId;
    }

    public void setWidthUomId(String widthUomId) {
        this.widthUomId = widthUomId;
    }

}
