package exercise1;

import java.util.Objects;

public class Goods {
    private String vendorCode;
    private String name;
    private String unit;
    private String quantity;
    private String price;

    public Goods(String vendorCode, String name, String unit, String quantity, String price) {
        setVendorCode(vendorCode);
        setName(name);
        setUnit(unit);
        setQuantity(quantity);
        setPrice(price);
    }

    public String redact(String var){
        if(!var.equals("")) {
            var = var.replaceAll("&&", ",");
            char[] array = var.toCharArray();
            if (array[0] == '"') {
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i < array.length - 1; i++) {
                    sb.append(array[i]);
                }
                return sb.toString();
            }
        }
        return var;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = redact(vendorCode);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = redact(name);
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = redact(unit);
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = redact(quantity);
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {

        this.price = redact(price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goods goods = (Goods) o;
        return Objects.equals(vendorCode, goods.vendorCode) && Objects.equals(name, goods.name) && Objects.equals(unit, goods.unit) && Objects.equals(quantity, goods.quantity) && Objects.equals(price, goods.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vendorCode, name, unit, quantity, price);
    }

    @Override
    public String toString() {
        return "Goods{" +
                "vendorCode='" + vendorCode + '\'' +
                ", name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                ", quantity='" + quantity + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
