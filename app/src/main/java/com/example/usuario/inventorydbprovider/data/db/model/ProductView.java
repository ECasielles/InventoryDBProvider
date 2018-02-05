package com.example.usuario.inventorydbprovider.data.db.model;


import android.os.Parcel;

/**
 * Created by usuario on 1/02/18.
 */

public class ProductView extends Product {
    public static final String TAG = "ProductView";

    public static final Creator<ProductView> CREATOR = new Creator<ProductView>() {
        @Override
        public ProductView createFromParcel(Parcel in) {
            return new ProductView(in);
        }

        @Override
        public ProductView[] newArray(int size) {
            return new ProductView[size];
        }
    };

    String categoryName;
    String productClassDescription;
    String sectorName;

    public ProductView(int id, int dependencyId, String serial, String modelCode, String shortname, String description, int category, int productClass, int sectorID, int quantity, float value, String vendor, int bitmap, String imageName, String url, String datePurchase, String notes, String categoryName, String productClassDescription, String sectorName) {
        super(id, dependencyId, serial, modelCode, shortname, description, category, productClass, sectorID, quantity, value, vendor, bitmap, imageName, url, datePurchase, notes);
        this.categoryName = categoryName;
        this.productClassDescription = productClassDescription;
        this.sectorName = sectorName;
    }

    public ProductView(Product product) {
        super(product.get_ID(), product.getDependencyId(), product.getSerial(),
                product.getModelCode(), product.getShortname(), product.getDescription(),
                product.getCategory(), product.getProductClass(), product.getSectorID(),
                product.getQuantity(), product.getValue(), product.getVendor(),
                product.getBitmap(), product.getImageName(), product.getUrl(), product.getDatePurchase(),
                product.getNotes());
    }

    protected ProductView(Parcel in) {
        super(in);
        categoryName = in.readString();
        productClassDescription = in.readString();
        sectorName = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(categoryName);
        parcel.writeString(productClassDescription);
        parcel.writeString(sectorName);
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getProductClassDescription() {
        return productClassDescription;
    }

    public void setProductClassDescription(String productClassDescription) {
        this.productClassDescription = productClassDescription;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

}
