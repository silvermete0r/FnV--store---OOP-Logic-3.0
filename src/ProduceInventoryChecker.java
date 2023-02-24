import java.util.List;

class ProduceInventoryChecker implements ProduceInventoryObserver {
    public void update(List<Produce> produceList) {
        for (Produce produce : produceList) {
            if (produce.getPrice() < 1.0) { // if price is less than $1.00, notify the store manager
                System.out.println("Attention store manager: " + produce.getClass().getSimpleName() + " price is low.");
            }
        }
    }
}