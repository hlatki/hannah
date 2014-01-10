[:html
 {:xmlns "http://www.w3.org/1999/xhtml", :lang "en", :xml:lang "en"}
 [:head
  [:meta
   {:http-equiv "content-type", :content "text/html; charset=UTF-8"}]
  [:meta {:name "description", :content (:description metadata)}]
  [:meta {:name "keywords", :content (:tags metadata)}]
  [:meta {:name "author", :content "Hannah Atkinson"}]
  [:link {:rel "icon", 
	  :href "/images/favicon.ico" :type "image/x-icon"}]
  [:link {:rel "shortcut icon", 
	  :href "/images/favicon.ico" :type "image/x-icon"}]
  [:link {:rel "stylesheet", :type "text/css", :href "/bootstrap-notypography.css"}]
  [:link {:rel "stylesheet", :type "text/css", :href "/narrow.css"}]
  [:link {:rel "stylesheet", :type "text/css", :href "/typeplate.css"}]

  [:link
   {:rel "alternate", :type "application/rss+xml",
    :title (:site-title (static.config/config)), :href "/rss-feed"}]

  (if (= (:type metadata) :post)
    [:link {:rel "canonical" 
	    :href (str "http://hannah.io" (:url metadata))}])

  [:script {:src "http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML",
            :type "text/javascript"}]
  [:title (:title metadata)]]
 [:body
  [:div
   {:class "container"}
   [:div
    {:class "header"}
    [:ul 
      {:class "nav nav-pills pull-right"}
      [:li [:a {:href "/", :class "page"} "Home"]] 
      [:li [:a {:href "/about.html", :class "page"} "About"]] 
      [:li [:a {:href "https://github.com/hlatki", :class "page"} "GitHub"]] 
      [:li [:a {:href "https://twitter.com/hlatkin", :class "page"} "Twitter"]]]
    [:h1
         [:a
          {:href "http://hannah.io"}
          "hannah!"
          ]]]
   [:div
    {:class "content"}
    [:div
     {:class "post"}
     (if (or (= (:type metadata) :post)
	     (= (:type metadata) :site)) 
       [:h3 {:class "page-title"} (:title metadata)])

     content

     (if (= (:type metadata) :post)
       (reduce 
     	(fn[h v]
     	  (conj h [:a {:href (str "/tags/#" v)} (str v " ")]))
     	[:div {:class "post-tags"} "Tags: "] 
     	(.split (:tags metadata) " ")))]

    (if (= (:type metadata) :post)
      [:div
       {:id "related"}
       [:h4 {:class "random-posts"} "Random Posts"]
       [:ul
    	{:class "posts"}
	(map 
	 #(let [f %
		url (static.core/post-url f)
		[metadata _] (static.io/read-doc f)
		date (static.core/parse-date 
		      "yyyy-MM-dd" "dd MMM yyyy" 
		      (re-find #"\d*-\d*-\d*" (str f)))]
	   [:li [:span date] [:a {:href url} (:title metadata)]]) 
	 (take 5 (shuffle (static.io/list-files :posts))))]])

    [:div {:id "disqus"} 
     (if (= (:type metadata) :post) 
       "<div id=\"disqus_thread\"></div><script type=\"text/javascript\" src=\"http://disqus.com/forums/hannahio/embed.js\"></script><noscript><a href=\"http://disqus.com/forums/hannahio/?url=ref\">View the discussion thread.</a></noscript><a href=\"http://disqus.com\" class=\"dsq-brlink\">blog comments powered by <span class=\"logo-disqus\">Disqus</span></a>")]]
   [:div
    {:class "footer"}
    [:a {:href "/rss-feed"} " RSS Feed"]
    [:p "&copy; 2013" 
     [:a {:href "http://hannah.io"} " Hannah Atkinson"]]
    [:p "Built with " [:a {:href "http://nakkaya.com/static.html"} "Static"]]]]
  ;;
  ;;
  (if (= (:type metadata) :post) 
    "<script type=\"text/javascript\">
//<![CDATA[
(function() {
	     var links = document.getElementsByTagName('a');
	     var query = '?';
	     for(var i = 0; i < links.length; i++) {
		     if(links[i].href.indexOf('#disqus_thread') >= 0) {
								       query += 'url' + i + '=' + encodeURIComponent(links[i].href) + '&';
								       }
		     }
	     document.write('<script charset=\"utf-8\" type=\"text/javascript\" src=\"http://disqus.com/forums/hannahio/get_num_replies.js' + query + '\"></' + 'script>');
	     })();
//]]>
</script>")]]
