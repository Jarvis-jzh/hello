package person.jzh.hello.chain.servlet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/5/5 16:58
 * @description
 */
public class FilterChain implements Filter {
    List<Filter> filters = new ArrayList<>();
    int index = 0;

    public FilterChain add(Filter filter) {
        filters.add(filter);
        return this;
    }

//    public void doFilter(Request request, Response response) {
//        if (index == filters.size())
//            return;
//        Filter filter = filters.get(index);
//        index++;
//        filter.doFilter(request, response, this);
//    }

    @Override
    public boolean doFilter(Request req, Response resp, FilterChain chain) {
        if (index == filters.size())
            return true;
        Filter filter = filters.get(index);
        index++;
        return filter.doFilter(req, resp, this);
    }
}
