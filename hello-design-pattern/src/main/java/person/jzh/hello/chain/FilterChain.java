package person.jzh.hello.chain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/5/5 16:21
 * @description
 */
public class FilterChain implements Filter {
    List<Filter> filters = new ArrayList<>();

    public FilterChain add(Filter f) {
        filters.add(f);
        return this;
    }

    public FilterChain delete(Filter f) {
        filters.remove(f);
        return this;
    }

    public boolean doFilter(Msg msg) {
        for (Filter f : filters) {
            if (!f.doFilter(msg))
                return false;
        }
        return true;
    }
}
