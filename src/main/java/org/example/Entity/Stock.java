 package org.example.Entity;

    public class Stock {

        private int id;
        private int itemId;
        private int quantity;
        private int totalPrice;

        public Stock(int itemId, int quantity) {
            this.itemId = itemId;
            this.quantity = quantity;
        }

        public Stock(int id, int itemId, int quantity, int totalPrice) {
            this.id = id;
            this.itemId = itemId;
            this.quantity = quantity;
            this.totalPrice = totalPrice;
        }

        public int getId() {
            return id;
        }

        public int getItemId() {
            return itemId;
        }

        public int getQuantity() {
            return quantity;
        }

        public int getTotalPrice() {
            return totalPrice;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return "unit{" +
                    "id=" + id +
                    ", itemId=" + itemId +
                    ", quantity=" + quantity +
                    ", totalPrice=" + totalPrice +
                    '}';
        }
    }

