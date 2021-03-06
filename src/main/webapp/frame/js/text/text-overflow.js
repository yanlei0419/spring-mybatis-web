(function(c) {
    var b = document.documentElement.style,
    d = ("textOverflow" in b || "OTextOverflow" in b),
    a = function(f, i) {
        var h = 0,
        e = [],
        g = function(j) {
            var l = 0,
            k;
            if (h > i) {
                return
            }
            for (l = 0; l < j.length; l += 1) {
                if (j[l].nodeType === 1) {
                    k = j[l].cloneNode(false);
                    e[e.length - 1].appendChild(k);
                    e.push(k);
                    g(j[l].childNodes);
                    e.pop()
                } else {
                    if (j[l].nodeType === 3) {
                        if (h + j[l].length < i) {
                            e[e.length - 1].appendChild(j[l].cloneNode(false))
                        } else {
                            k = j[l].cloneNode(false);
                            k.textContent = c.trim(k.textContent.substring(0, i - h));
                            e[e.length - 1].appendChild(k)
                        }
                        h += j[l].length
                    } else {
                        e.appendChild(j[l].cloneNode(false))
                    }
                }
            }
        };
        e.push(f.cloneNode(false));
        g(f.childNodes);
        return c(e.pop().childNodes)
    };
    c.extend(c.fn, {
        textOverflow: function(g, e) {
            var f = g || "&#x2026;";
            if (!d) {
                return this.each(function() {
                    var l = c(this),
                    m = l.clone(),
                    p = l.clone(),
                    k = l.text(),
                    h = l.width(),
                    n = 0,
                    o = 0,
                    j = k.length,
                    i = function() {
                        if (h !== l.width()) {
                            l.replaceWith(p);
                            l = p;
                            p = l.clone();
                            l.textOverflow(g, false);
                            h = l.width()
                        }
                    };
                    l.after(m.hide().css({
                        position: "absolute",
                        width: "auto",
                        overflow: "visible",
                        "max-width": "inherit"
                    }));
                    if (m.width() > h) {
                        while (n < j) {
                            o = Math.floor(n + ((j - n) / 2));
                            m.empty().append(a(p.get(0), o)).append(f);
                            if (m.width() < h) {
                                n = o + 1
                            } else {
                                j = o
                            }
                        }
                        if (n < k.length) {
                            l.empty().append(a(p.get(0), n - 1)).append(f)
                        }
                    }
                    m.remove();
                    if (e) {
                        setInterval(i, 200)
                    }
                })
            } else {
                return this
            }
        }
    })
})(jQuery);
$(document).ready(function() {
    $(".text_slice").textOverflow("...")
});