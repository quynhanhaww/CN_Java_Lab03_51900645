import java.util.List;

public interface Repository<T> {
    public boolean add(T item);
    public T get(int id);
    public List<T> getAll();
    public boolean remove(int id);
    public boolean remove(T item);
    public boolean update(T item);
}
