package example.day10;

public class GenericArrayList <T> {
    T[] array;
    int size = 0;
    public GenericArrayList(){
        this.array = (T[]) new Object[this.size];
    }

    public boolean add(T value){
        this.size++;
        T[]newArray = (T[]) new Object[this.size];
        for (int i = 0; i < this.array.length; i++){
            newArray[i] = this.array[i];
        }
        newArray[size-1] = value;
        this.array = newArray;
        return false;
    }

    public boolean remove(int index){
        if (this.size == 0){
            return false;
        }
        else if (this.size == 1){
            this.array = (T[]) new Object[0];
            this.size--;
            return true;
        }
        else if (index == this.size-1){
            T[] newArray = (T[]) new Object[size-1];
            for (int i = 0; i < newArray.length; i++){
                newArray[i] = this.array[i];
            }
            this.array = newArray;
            this.size--;
            return true;
        }
        else if (index < this.size){
            T[] newArray = (T[]) new Object[size-1];
            for (int i = 0; i < newArray.length; i++){
                if(index <= i){
                    newArray[i] = this.array[i+1];
                } else {
                    newArray[i] = this.array[i];
                }
            }
            this.array = newArray;
            this.size--;
            return true;
        }
        else {return false;}
    }

    public T get(int index){
        if (this.array.length > index) {
            return this.array[index];
        }
        else {return null;}
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("GenericArrayList = [");
        for (int i = 0; i < this.array.length; i++){
            builder.append(array[i]);
            if (i + 1 == this.array.length){
                builder.append("]");
            } else {
                builder.append(", ");
            }
        }
        return builder.toString();
    }
}
