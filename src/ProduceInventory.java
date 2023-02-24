import java.util.ArrayList;
import java.util.List;

class ProduceInventory {
    List<Produce> produceList;
    private List<ProduceInventoryObserver> observers;

    public ProduceInventory() {
        produceList = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public void addProduce(Produce produce) {
        produceList.add(produce);
    }

    public void removeProduce(Produce produce) {
        produceList.remove(produce);
    }

    public void addObserver(ProduceInventoryObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ProduceInventoryObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (ProduceInventoryObserver observer : observers) {
            observer.update(produceList);
        }
    }
}
