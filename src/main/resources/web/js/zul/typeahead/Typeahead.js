zul.typeahead.Typeahead = zk.$extends(zk.Widget, {
    $define: {
        hint: null
    },
    bind_: function () {
        this.$supers(zul.typeahead.Typeahead, 'bind_', arguments);
        console.log(this);
        console.log('Typeahead.js: hint=' + this.getHint());
        console.log('Typeahead.js: preparing substring matcher');
        var states = ['Alabama', 'Alaska', 'Arizona', 'Arkansas', 'California',
            'Colorado', 'Connecticut', 'Delaware', 'Florida', 'Georgia', 'Hawaii',
            'Idaho', 'Illinois', 'Indiana', 'Iowa', 'Kansas', 'Kentucky', 'Louisiana',
            'Maine', 'Maryland', 'Massachusetts', 'Michigan', 'Minnesota',
            'Mississippi', 'Missouri', 'Montana', 'Nebraska', 'Nevada', 'New Hampshire',
            'New Jersey', 'New Mexico', 'New York', 'North Carolina', 'North Dakota',
            'Ohio', 'Oklahoma', 'Oregon', 'Pennsylvania', 'Rhode Island',
            'South Carolina', 'South Dakota', 'Tennessee', 'Texas', 'Utah', 'Vermont',
            'Virginia', 'Washington', 'West Virginia', 'Wisconsin', 'Wyoming'
        ];
        var statehound = new Bloodhound({
            datumTokenizer: Bloodhound.tokenizers.whitespace,
            queryTokenizer: Bloodhound.tokenizers.whitespace,
            local: states
        });
        console.log('Typeahead.js: initializing typeahead');
        $('#' + this.uuid).typeahead({
                hint: this.getHint(),
                highlight: true,
                minLength: 1
            },
            {
                name: 'states',
                source: statehound
            });
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