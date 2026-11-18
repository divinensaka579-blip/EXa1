public class ProductSales implements IProductSales {
    private final int SALES_LIMIT = 300;
    private int[][] productSales;

    public ProductSales() {
        productSales = GetProductSales();
    }

    @Override
    public int[][] GetProductSales() {
        return new int[][]{
                {300, 150, 700},
                {250, 200, 600}
        };
    }

    @Override
    public int GetTotalSales() {
        int total = 0;
        for (int[] year : productSales) {
            for (int sale : year) {
                total += sale;
            }
        }
        return total;
    }

    @Override
    public int GetSalesOverLimit() {
        int count = 0;
        for (int[] year : productSales) {
            for (int sale : year) {
                if (sale > SALES_LIMIT) count++;
            }
        }
        return count;
    }

    @Override
    public int GetSalesUnderLimit() {
        int count = 0;
        for (int[] year : productSales) {
            for (int sale : year) {
                if (sale <= SALES_LIMIT) count++;
            }
        }
        return count;
    }

    @Override
    public int GetProductsProcessed() {
        return productSales.length;
    }

    @Override
    public double GetAverageSales() {
        return (double) GetTotalSales() / 6;
    }
}