zul.typeahead.Typeahead = zk.$extends(zk.Widget, {
    $define: {
        highlight: null,
        hint: null,
        minLength: null,
        dataset: null
    },
    getNativeDataset: function () {
        console.log(this);
        var dataset = this.getDataset();
        console.log(dataset);
        var source = dataset['source'];
        if ('_class' in source) {
            var config = {
                datumTokenizer: Bloodhound.tokenizers.whitespace,
                queryTokenizer: Bloodhound.tokenizers.whitespace
            };
            if ('local' in source) {
                console.log('Configuring local dataset..');
                config['local'] = source['local'];
            }
            if ('prefetch' in source) {
                console.log('Configuring prefetch dataset..');
                config['prefetch'] = source['prefetch'];
            }
            if ('remote' in source) {
                console.log('Configuring remote dataset..');
                config['remote'] = source['remote'];
            }
            dataset['source'] = new Bloodhound(config);
        }
        return dataset;
    },
    bind_: function () {
        this.$supers(zul.typeahead.Typeahead, 'bind_', arguments);
        console.log(this);
        var config = {
            hint: this.getHint(),
            highlight: this.getHighlight(),
            minLength: this.getMinLength()
        };
        console.log('Typeahead.js: preparing native dataset');
        console.log(this.getDataset());
        var dataset = this.getNativeDataset();
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
    }
});