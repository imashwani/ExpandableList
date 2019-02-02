package com.example.expandablelist.model;

public class SubClothItem{
        String subClothName;
        boolean isChecked;

        public SubClothItem(String subClothName, boolean isChecked) {
            this.subClothName = subClothName;
            this.isChecked = isChecked;
        }

        public String getSubClothName() {
            return subClothName;
        }

        public void setSubClothName(String subClothName) {
            this.subClothName = subClothName;
        }

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }
    }
