public class task {
    // Абстрактный класс Мебель
    public static abstract class Furniture {
        private String material;
        private double price;

        public Furniture(String material, double price) {
            this.material = material = "Дерево";
            this.price = price = 12;
        }

        public String getMaterial() {
            return material;
        }

        public void setMaterial(String material) {
            this.material = material;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        // Абстрактный метод для вывода информации о мебели
        public abstract void displayInfo();

        // Метод для расчета стоимости доставки
        public double calculateShippingCost(double distance) {
            // Реализация расчета стоимости доставки
            return distance * 0.1;
        }
        public int calculateShippingCost(int distance) {
            return distance + 50;
        }
    }

    // Класс Стол, наследующийся от Мебели
    public static class Table extends Furniture {
        private int numberOfLegs;

        public Table(String material, double price, int numberOfLegs) {
            super(material, price);
            this.numberOfLegs = numberOfLegs;
        }

        public Table() {
        super("Дерево", 100.0); // Устанавливаем значения по умолчанию для материала и цены
        // numberOfLegs = 4; // Устанавливаем значение по умолчанию для числа ног
    }

        public int getNumberOfLegs() {
            return numberOfLegs;
        }

        public void setNumberOfLegs(int numberOfLegs) {
            this.numberOfLegs = numberOfLegs;
        }

        @Override
        public void displayInfo() {
            System.out.println("Стол из материала: " + getMaterial() + ", с " + numberOfLegs + " ногами.");
            System.out.println("Стоимость: " + getPrice() + " руб.");
        }
    }

    // Класс Стул, наследующийся от Мебели
    public static class Chair extends Furniture {
        private boolean hasArmrests = true;
        static int count = 0;

        public Chair(String material, double price, boolean hasArmrests) {
            super(material, price);
            this.hasArmrests = hasArmrests;
            count += 1;
        }

        public int getCount() {
            return count;
        }

        public boolean isHasArmrests() {
            return hasArmrests;
        }

        public void setHasArmrests(boolean hasArmrests) {
            this.hasArmrests = hasArmrests;
        }

        @Override
        public void displayInfo() {
            String armrestsInfo = hasArmrests ? "с подлокотниками" : "без подлокотников";
            System.out.println("Стул из материала: " + getMaterial() + ", " + armrestsInfo + ".");
            System.out.println("Стоимость: " + getPrice() + " руб.");
        }
    }

    // Класс Кровать, наследующийся от Мебели
    public static class Bed extends Furniture {
        private int numberOfSleepers = 2;

        public Bed(String material, double price, int numberOfSleepers) {
            super(material, price);
            this.numberOfSleepers = numberOfSleepers;
        }
        

        public int getNumberOfSleepers() {
            return numberOfSleepers;
        }

        public void setNumberOfSleepers(int numberOfSleepers) {
            this.numberOfSleepers = numberOfSleepers;
        }

        @Override
        public void displayInfo() {
            System.out.println("Кровать из материала: " + getMaterial() + ", для " + numberOfSleepers + " спальных мест.");
            System.out.println("Стоимость: " + getPrice() + " руб.");
        }
    }

    public static void main(String[] args) {
        // Создание объектов и вывод информации о них
        Table table = new Table("Дерево", 500, 4);
        Chair chair = new Chair("Пластик", 100, false);
        Bed bed = new Bed("Металл", 800, 2);
        Table table1 = new Table();
        System.out.println(table1.getNumberOfLegs());
        // table.displayInfo();
        table.displayInfo();
        chair.displayInfo();
        bed.displayInfo();
        System.out.println(chair.getCount());
        Chair chair2 = new Chair("Пластик", 100, false);
        System.out.println(chair.getCount());

        
    }
}

