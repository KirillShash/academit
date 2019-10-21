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
        return this.to - this.from;
    }

    public boolean isInside(double number) {
        return (number <= to) && (number >= from);
    }

    public Range getIntersection(Range range2) {
        if (from >= range2.to || range2.from >= to) {
            return null;
        }
        if (range2.from >= from && range2.to <= to) {
            return new Range(range2.from, range2.to);
        }

        if (range2.from <= from && range2.to > to) {
            return new Range(from, to);
        }

        if (from < range2.from && to < range2.to) {
            return new Range(range2.from, to);
        }

        return new Range(from, range2.to);
    }

    public Range[] getUnion(Range range2) {
        if (from > range2.to || range2.from > to) {
            return new Range[]{new Range(from, to), new Range(range2.from, range2.to)};
        }

        if (from < range2.from && to > range2.to) {
            return new Range[]{new Range(from, to)};
        }

        if (from >= range2.from && to <= range2.to) {
            return new Range[]{new Range(range2.from, range2.to)};
        }

        if (from < range2.from && to <= range2.to) {
            return new Range[]{new Range(from, range2.to)};
        }

        return new Range[]{new Range(range2.from, to)};
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
}
