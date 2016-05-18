function (out) {
    out.push('<input type="text"');
    if (this.getPlaceholder()) {
        out.push(' placeholder="', this.getPlaceholder(), '"');
    }
    if (this.getDir()) {
        out.push(' dir="', this.getDir(), '"');
    }
    out.push(this.domAttrs_(), '>');
}