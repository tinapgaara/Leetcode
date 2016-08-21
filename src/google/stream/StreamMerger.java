package google.stream;

import google.datastructure.IStream;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by yingtan on 11/8/15.
 */
public class StreamMerger implements IStream<Integer> {

    List<Integer> streams;
    int i;
    int maxSize;

    public StreamMerger() {
        streams = new ArrayList<>();
        i = 0;
        maxSize = 1000000000;
    }

    @Override
    public Integer peek() {
        return streams.get(streams.size()-1);
    }

    @Override
    public Integer next() {
        int res = streams.get(i);
        i ++;
        return res;
    }

    @Override
    public boolean hasNext() {
        if (i < maxSize) return true;
        return false;
    }

    @Override
    public void append(Integer x) {
        streams.add(x);
    }

    public void mergeKSortedStreams(StreamMerger[] streams) {
        PriorityQueue<Stream> queue = new PriorityQueue<Stream>(); // need to write a comparator

        for (int i = 0; i < streams.length; i++) { // k streams
            if (streams[i].hasNext())
                queue.offer(new Stream(streams[i].next(), i));
        }

        while (!queue.isEmpty()) {
            Stream stream = queue.poll();
            int index = stream.index;
            if (streams[index].hasNext()) {
                queue.offer(new Stream(streams[index].next(), index));
            }
        }
    }


    public class Stream {
        int index;
        int val;

        public Stream(int index, int val) {
            this.index = index;
            this.val = val;
        }
    }

}
