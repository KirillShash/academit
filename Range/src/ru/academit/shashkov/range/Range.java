package ru.academit.shashkov.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public double getTo() {
        return to;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return (number >= from) && (number <= to);
    }

    public Range getIntersection(Range range2) {
        double x = Math.max(from, range2.from);
        double y = Math.min(to, range2.to);

        if (x < y) {
            return new Range(x, y);
        }

        return null;
    }

    public Range[] getUnion(Range range2) {
        double x = Math.max(from, range2.from);
        double y = Math.min(to, range2.to);

        if (x <= y) {
            return new Range[]{new Range(Math.min(from, range2.from), Math.max(to, range2.to))};
        }

        return new Range[]{new Range(from, to), new Range(range2.from, range2.to)};
    }

    public Range[] getDifference(Range range2) {
        if (range2.from > to || range2.to < from) {
            return new Range[]{new Range(from, to)};
        }

        if (range2.from > from && range2.to < to) {
            return new Range[]{new Range(from, range2.from), new Range(range2.to, to)};
        }

        if (range2.from <= from && range2.to >= to) {
            return new Range[0];
        }

        if (range2.from > from && range2.to >= to) {
            return new Range[]{new Range(from, range2.from)};
        }

        return new Range[]{new Range(range2.to, to)};
    }

    @Override
    public String toString() {
        return "[" + from + " " + to + "]";
    }
}
