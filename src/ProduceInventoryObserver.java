import java.util.List;

interface ProduceInventoryObserver {
    void update(List<Produce> produceList);
}