zul.typeahead.Typeahead = zk.$extends(zul.inp.Textbox, {

    // fields

    $define: {
        highlight: null,
        hint: null,
        minLength: null,
        placeholder: null,
        dir: null,
        dataset: null
    },

    // protected

    bind_: function () {
        this.$supers(zul.typeahead.Typeahead, 'bind_', arguments);
        var config = {
            hint: this.getHint(),
            highlight: this.getHighlight(),
            minLength: this.getMinLength()
        };
        var ds = this._getNativeDataset();
        var widget = this;
        var component = $('#' + this.uuid);
        component.typeahead(config, ds);
        component.bind('typeahead:active', function () {
            widget.fire('onTypeaheadActive');
        });
        component.bind('typeahead:idle', function () {
            widget.fire('onTypeaheadIdle');
        });
        component.bind('typeahead:open', function () {
            widget.fire('onTypeaheadOpen');
        });
        component.bind('typeahead:close', function () {
            widget.fire('onTypeaheadClose');
        });
        component.bind('typeahead:change', function (target, type, bubbles, cancelable) {
            widget.fire('onTypeaheadChange');
        });
        component.bind('typeahead:render', function (ev, suggestions, async, datasetName) {
            widget.fire('onTypeaheadRender', suggestions);
        });
        component.bind('typeahead:select', function (ev, suggestion) {
            widget.updateChange_();
            widget.fire('onSelect', suggestion);
        });
        component.bind('typeahead:autocomplete', function (ev, suggestion) {
            widget.fire('onTypeaheadAutocomplete', suggestion);
        });

        component.bind('typeahead:cursorchange', function (ev, suggestion) {
            widget.fire('onTypeaheadCursorChange', suggestion);
        });
        component.bind('typeahead:asyncrequest', function (ev, query, datasetName) {
            widget.fire('onTypeaheadAsyncRequest', query);
        });
        component.bind('typeahead:asynccancel', function (ev, query, datasetName) {
            widget.fire('onTypeaheadAsyncCancel', query);
        });
        component.bind('typeahead:asyncreceive', function (ev, query, datasetName) {
            widget.fire('onTypeaheadAsyncReceive', query);
        });
    },
    domClass_: function (no) {
        var classes = this.$supers("domClass_", no) || '';
        if (classes) {
            classes += ' ';
        }
        classes += 'z-textbox';
        return classes.trim();
    },

    // private

    _getNativeDataset: function () {
        var ds = this.getDataset();
        ds['templates'] = this._toNativeDatasetTemplates(ds['templates']);
        ds['source'] = this._toNativeDatasetSource(ds['source']);
        return ds;
    },
    _toNativeDatasetTemplates: function (templates) {
        for (var key in templates) {
            if (templates.hasOwnProperty(key)) {
                templates[key] = Handlebars.compile(templates[key]);
            }
        }
        return templates;
    },
    _toNativeDatasetSource: function (source) {
        if ('_class' in source) {
            switch (source['_class']) {
                case 'bloodhound':
                    if (source.hasOwnProperty('prefetch')) {
                        source.prefetch = this._toNativeFunctions(source.prefetch);
                    }
                    if (source.hasOwnProperty('remote')) {
                        source.remote = this._toNativeFunctions(source.remote);
                    }
                    return this._toBloodhoundDataset(source);
                default:
                    throw 'Unsupported dataset source: "' + source['_class'] + '"'
            }
        }
    },
    _toNativeFunctions: function (sourceData) {
        if (sourceData.hasOwnProperty('prepare')) {
            sourceData.prepare = this._toFunction(sourceData.prepare);
        }
        if (sourceData.hasOwnProperty('transform')) {
            sourceData.transform = this._toFunction(sourceData.transform);
        }
        return sourceData;
    },
    _toFunction: function (fun) {
        if (typeof fun == 'string') {
            return eval('(' + fun + ')');
        } else {
            return fun;
        }
    },
    _toBloodhoundDataset: function (source) {
        source['datumTokenizer'] = this._toBloodhoundTokenizers(source['datumTokenizers']);
        source['queryTokenizer'] = this._toBloodhoundTokenizers(source['queryTokenizers']);
        return new Bloodhound(source);
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
                return $.map(nativeTokenizers, function (nativeTokenizer) {
                    return nativeTokenizer(datum);
                });
            }
        }
    },
    _toBloodhoundTokenizer: function (tokenizer) {
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
                throw 'Unsupported Bloodhound tokenizer type: "' + tokenizer['type'] + '"'
        }
    }
});