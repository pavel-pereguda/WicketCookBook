(function ($) {
	$.fn.enableRangeSelection = function()
	{
		all=this;
		last=null;
		$(all).bind("click", onclick);
	}
    
	function index(value)
	{
	    var idx=0;
	    $(all).each(function(i) { if (value == this.value ) { idx=i; } });
	    return idx;
	}

    function onclick(event)
    {
		var value=this.value;
		var checked=this.checked;
		var idx=index(value);

		if (last!=null&&(event.shiftKey||event.metaKey))
		{
			var min=Math.min(idx, last);
			var max=Math.max(idx, last);
			$(all).slice(min,max).each(function() {
				this.checked = checked;
			});
		} 
		last=idx;
    }
})(jQuery);