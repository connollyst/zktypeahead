zul.typeahead.Typeahead = zk.$extends(zk.Widget, {
    $define: {
        highlight: null,
        hint: null,
        minLength: null,
        dataset: null
    },
    bind_: function () {
        this.$supers(zul.typeahead.Typeahead, 'bind_', arguments);
        console.log(this);
        var config = {
            hint: this.getHint(),
            highlight: this.getHighlight(),
            minLength: this.getMinLength()
        };
        console.log('ZK Dataset:');
        console.log(this.getDataset());
        console.log('Typeahead.js: preparing native dataset:');
        var dataset = this._getNativeDataset();
        console.log(dataset);
        console.log('Typeahead.js: initializing typeahead widget');
        $('#' + this.uuid).typeahead(config, dataset);
    },
    domClass_: function (no) {
        var classes = this.$supers("domClass_", no) || '';
        if (classes) {
            classes += ' ';
        }
        classes += 'z-textbox';
        return classes.trim();
    },
    _getNativeDataset: function () {
        var dataset = this.getDataset();
        console.log(dataset);
        var source = dataset['source'];
        if ('_class' in source) {
            switch (source['_class']) {
                case 'bloodhound':
                    source = this._toBloodhoundDataset(source);
                    break;
                default:
                // TODO error
            }
        }
        dataset['source'] = source;
        return dataset;
    },
    _toBloodhoundDataset: function (source) {
        var type = source['_type'];
        var config = {
            datumTokenizer: this._toBloodhoundTokenizers(source['datumTokenizers']),
            queryTokenizer: this._toBloodhoundTokenizers(source['queryTokenizers']),
        };
        config[type] = source[type];
        return new Bloodhound(config);
    },
    _toBloodhoundTokenizers: function (tokenizers) {
        if (tokenizers == undefined) {
            return null;
        } else if (tokenizers.length == 1) {
            return this._toBloodhoundTokenizer(tokenizers[0]);
        } else {
            var widget = this;
            var nativeTokenizers = $.map(tokenizers, function (tokenizer) {
                return widget._toBloodhoundTokenizer(tokenizer);
            });
            return function customTokenizer(datum) {
                var tokenized = $.map(nativeTokenizers, function (nativeTokenizer) {
                    return nativeTokenizer(datum);
                });
                console.log('Tokenized data: ' + tokenized);
                return tokenized;
            }
        }
    },
    _toBloodhoundTokenizer: function (tokenizer) {
        console.log(tokenizer);
        var hasKey = ('key' in tokenizer);
        var key = tokenizer['key'];
        var t = this._toBloodhoundRawTokenizer(tokenizer);
        if (!hasKey) {
            return t
        } else {
            return function (datum) {
                return t(datum[key]);
            }
        }
    },
    _toBloodhoundRawTokenizer: function (tokenizer) {
        switch (tokenizer['type']) {
            case 'nonword':
                return Bloodhound.tokenizers.nonword;
            case 'whitespace':
                return Bloodhound.tokenizers.whitespace;
            default:
                throw "Unsupported Bloodhound tokenizer type: '" + tokenizer['type'] + "'"
        }
    }
});