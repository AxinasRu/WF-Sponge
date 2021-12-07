package wfplugin.wfplugin.storage;

import java.util.ArrayList;
import java.util.Objects;

import static wfplugin.wfplugin.WFPlugin.log;

public class Region2d {
    public ArrayList<Region2d> removed = new ArrayList<>();
    private Position2d start, stop;
    public int id = -1;

    public Region2d(Position2d start, Position2d stop) {
        this.start = start;
        this.stop = stop;
    }

    public Region2d() {
    }

    public void setStart(Position2d start) {
        this.start = start;
    }

    public void setStop(Position2d stop) {
        this.stop = stop;
    }

    public Position2d getStart() {
        return start;
    }

    public Position2d getStop() {
        return stop;
    }

    public Region2d getCorrected() {
        if (start == null || stop == null) return this;
        return new Region2d(
                new Position2d(Math.min(start.x, stop.x), Math.min(start.z, stop.z)),
                new Position2d(Math.max(start.x, stop.x), Math.max(start.z, stop.z))
        );
    }

    public int size() {
        Region2d corrected = getCorrected();
        if (corrected.start == null || corrected.stop == null) return 0;
        return (corrected.stop.x - corrected.start.x + 1) * (corrected.stop.z - corrected.start.z + 1) - emptySize();
    }

    @Override
    public String toString() {
        return "{" +
                "start:" + start +
                ", stop:" + stop +
                '}';
    }

    public boolean contains(Position2d location) {
        for (Region2d region2d : removed)
            if (region2d.contains(location))
                return false;
        Region2d corrected = getCorrected();
        return corrected.start.x <= location.x && location.x <= corrected.stop.x &&
                corrected.start.z <= location.z && location.z <= corrected.stop.z;
    }

    public boolean contains(Region2d region2D) {
        boolean contains = contains(region2D.start);
        boolean contains1 = contains(region2D.stop);
        return contains && contains1;
    }

    public boolean intersected(Region2d region2D) {
        return contains(region2D.start) || contains(region2D.stop);
    }

    public Region2d getIntersection(Region2d reg) {
        int startX = Math.max(getStart().x, reg.getStart().x);
        int startZ = Math.max(getStart().z, reg.getStart().z);
        int stopX = Math.min(getStop().x, reg.getStop().x);
        int stopZ = Math.min(getStop().z, reg.getStop().z);

        if (startX > stopX || startZ > stopZ)
            return null;
        return new Region2d(new Position2d(startX, startZ), new Position2d(stopX, stopZ));
    }

    public boolean isEmpty() {
        return size() <= 0;
    }

    private int emptySize() {
        int removedSize = 0;
        for (Region2d region2d : removed)
            removedSize += region2d.size();
        return removedSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Region2d region2d = (Region2d) o;
        return Objects.equals(start, region2d.start) && Objects.equals(stop, region2d.stop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, stop);
    }
}
